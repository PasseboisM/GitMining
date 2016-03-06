package common.util;

import java.util.List;

public class DataSourceProducer<T> implements Runnable {
	

	ObjChannel<T> chan;
	List<T> dataSource;
	
	public DataSourceProducer(ObjChannel<T> channel,List<T> dataSource) {
		this.chan = channel;
		this.dataSource = dataSource;
	}
	public void run() {
		@SuppressWarnings("unchecked")
		T[] dataSourceArray = (T[]) dataSource.toArray();
		chan.writeObj(dataSourceArray);
		chan.close();
	}

}
