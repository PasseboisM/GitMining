package common.model;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import common.exception.DataTransferException;
import common.util.ObjChannel;

public class ObjChannelWithBlockingQueue<T> implements ObjChannel<T> {

	public static final int NORMAL = 0;
	public static final int EXCEPTION_INVOKED = 1;
	
	
	volatile int status;
	
	BlockingQueue<T> queue = new LinkedBlockingQueue<T>(200);
	
	public void writeObj(T[] list) {
		for(T obj: list) {
			queue.add(obj);
		}
	}

	public List<T> getObj(int maxNum) throws DataTransferException {
		
		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void closeWithException() {
		// TODO Auto-generated method stub
		
	}

	public boolean hasMore() {
		// TODO Auto-generated method stub
		return false;
	}

	public ObjChannelWithBlockingQueue() {
		super();
		status = NORMAL;
	}
}
