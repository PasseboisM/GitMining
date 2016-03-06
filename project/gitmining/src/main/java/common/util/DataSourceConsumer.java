package common.util;

import java.util.LinkedList;
import java.util.List;

import common.exception.DataTransferException;

public class DataSourceConsumer<T> implements Runnable {

	static final int page = 50;
	ObjChannel<T> channel = null;
	List<T> get = new LinkedList<T>();
	
	public DataSourceConsumer(ObjChannel<T> chan) {
		this.channel = chan;
	}
	
	public void run() {
		while (channel.hasMore()) {
			try {
				List<T> list = channel.getObj(page);
				System.out.println("Got a list, size=="+list.size());
				get.addAll(list);
			} catch (DataTransferException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public List<T> getData() {
		return get;
	}

}
