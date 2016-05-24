package common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import common.util.MultiSourceSwitch;
import common.util.ObjChannel;

/**
 * 默认版MultiSourceSwitch实现类
 * @author xjh14
 *
 * @param <T>
 */
public class BasicSourceSwitch<T> implements MultiSourceSwitch<T> {

	ReentrantLock lock = new ReentrantLock();
	
	private static final int WORKING = 0;
	private static final int EXCEPTION_INVOKED = 1;
//	private static final int NORMAL_CLOSED = 2;
//	private static final int EXCEPTIONAL_CLOSED = 3;
	
	private ObjChannel<T> chan;
	private volatile List<Object> sources = new ArrayList<Object>();
	private int status = WORKING;
	
	public BasicSourceSwitch(ObjChannel<T> channel) {
		chan = channel;
	}
	
	public void register(Object source) {
		lock.lock();
		if(!sources.contains(source)) {
			sources.add(source);
		}
		lock.unlock();
	}

	public void writeObj(T[] toBeWritten) {
		chan.writeObj(toBeWritten);
	}

	public void writeObj(List<T> toBeWritten) {
		chan.writeObj(toBeWritten);
	}

	public void deregister(Object source) {
		lock.lock();
		removeSource(source);
		checkStatus();
		lock.unlock();
	}

	public void declareException(Object source) {
		lock.lock();
		removeSource(source);
		status = EXCEPTION_INVOKED;
		checkStatus();
		lock.unlock();
	}

	/**
	 * 尝试从集流器中取消一个数据源
	 * @param source
	 */
	private void removeSource(Object source) {
		assert sources.contains(source):"该对象并未向集流器注册！";
		sources.remove(source);
	}
	
	/**
	 * 检查集流器工作状态，若没有数据源，则关闭关联的通道
	 */
	private void checkStatus() {
		if(sources.size()==0) {
			if(status==WORKING) {
				chan.close();
			} else {
				chan.closeWithException();
			}
		}
	}
}
