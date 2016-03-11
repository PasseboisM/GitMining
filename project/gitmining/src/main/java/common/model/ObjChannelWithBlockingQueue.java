package common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import common.exception.DataTransferException;
import common.util.ObjChannel;

/**
 * 
 * @author xjh14
 * 
 * @param <T> 传输数据的类型
 * 
 * 本通道仅可拥有一个数据源，由这个数据源操控进行开启、关闭；
 * 本通道可以接受多个数据接收者。
 */
public class ObjChannelWithBlockingQueue<T> implements ObjChannel<T> {

	public static final int NORMAL_RUNNING = 0;
	public static final int EXCEPTION_CLOSED = 1;
	public static final int NORMAL_CLOSED = 2;
	
//	ReentrantLock lock = new ReentrantLock();
	
	volatile int status;
	
	BlockingQueue<T> queue = new LinkedBlockingQueue<T>();
	
	public void writeObj(T[] list) {
//		lock.lock();
		for(T obj: list) {
			queue.add(obj);
		}
//		lock.unlock();
	}

	public List<T> getObj(int maxNum) throws DataTransferException {
		
//		lock.lock();
		
		List<T> result = new ArrayList<T>(maxNum);
		int currentNum = 0;
		
		BuildingList:
		while (currentNum<maxNum) {
			try {
				result.add(queue.remove());
				currentNum++;
			} catch (Exception e) {//queue is empty
				switch(status) {
				case NORMAL_RUNNING: break;
				case NORMAL_CLOSED: break BuildingList;
				case EXCEPTION_CLOSED: throw new DataTransferException();
				}
			}
		}

//		lock.unlock();
		
		return result;
	}

	public void close() {
		status = NORMAL_CLOSED;
	}

	public void closeWithException() {
		status = EXCEPTION_CLOSED;
	}

	public boolean hasMore() {
		
		boolean queueEmpty = queue.isEmpty();
		boolean sourceRunning = (status==NORMAL_RUNNING);
		
		return (!queueEmpty)||(sourceRunning);
	}

	public ObjChannelWithBlockingQueue() {
		super();
		status = NORMAL_RUNNING;
	}

	public void writeObj(List<T> list) {
//		lock.lock();
		queue.addAll(list);
//		lock.unlock();
	}
}
