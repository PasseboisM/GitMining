package software.nju.gitmining;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import common.exception.DataTransferException;
import common.model.ObjChannelWithBlockingQueue;
import common.util.ObjChannel;

/**
 * 本测试用例套件用于对系统中的ChannelWithBlockingQueue类进行功能测试
 * @author xjh14
 * Ver: 1.0
 */
public class ChannelWithBlockingQueueTest {

	Random rand = new Random();
	
	/**
	 * 测试在只有一个Consumer的情况下Channel的功能性。
	 */
	@Test
	public void testSingleConsumer() throws InterruptedException {
		
		int numOfObj = 5000;
		
		ObjChannel<MyData> chan = new ObjChannelWithBlockingQueue<MyData>();
		
		Producer p = new Producer(chan, 1, numOfObj);
		Consumer c = new Consumer(chan);
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		
		long time1 = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
		
		List<MyData> result = c.getData();
		
		assertEquals(numOfObj, result.size());
		
	}
	
	/**
	 * 测试在有多个Consumer的情况下ChannelWithBlockingQueue的同步性是否良好
	 */
	@Test
	public void testMultipleConsumer() throws Exception {
		
		int numOfObj = 5000;
		
		ObjChannel<MyData> chan = new ObjChannelWithBlockingQueue<MyData>();
		
		Producer p = new Producer(chan, 1, numOfObj);
		Consumer c1 = new Consumer(chan);
		Consumer c2 = new Consumer(chan);
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c1);
		Thread t3 = new Thread(c2);
		
		long time1 = System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
		
		List<MyData> result = c1.getData();
		result.addAll(c2.getData());
		
		assertEquals(numOfObj, result.size());
		
	}

	class MyData {
		
		int digit = 0;
		int producer;
		
		public MyData(int source){
			digit = rand.nextInt();
			producer = source;
		}
		
		public String toString() {
			return "Data:"+digit+", source:"+producer;
		}
	}
	
	class Producer implements Runnable {
		
		static final int page = 25;
		
		ObjChannel<MyData> chan;
		int num;
		int quantity;
		
		public Producer(ObjChannel<MyData> channel,int num, int produceQuantity) {
			chan = channel;
			this.num = num;
			this.quantity = produceQuantity;
		}

		public void run() {
			int produced = 0;
			ArrayList<MyData> list = new ArrayList<MyData>(page);
			for(;produced<quantity;produced++) {
				list.add(new MyData(num));
				if(list.size()==page) {
					MyData[] arr = new MyData[page];
					chan.writeObj(list.toArray(arr));
					list = new ArrayList<MyData>(page);
				}
//				try {
//					Thread.sleep(5);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			chan.close();
		}
	}
	
	class Consumer implements Runnable {

		static final int page = 23;
		
		List<MyData> get = new LinkedList<MyData>();
		
		ObjChannel<MyData> chan = null;
		
		public Consumer(ObjChannel<MyData> chan) {
			this.chan = chan;
		}
		
		public void run() {
			while (chan.hasMore()) {
				try {
					List<MyData> list = chan.getObj(page);
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
		public List<MyData> getData() {
			return get;
		}
		
	}
}
