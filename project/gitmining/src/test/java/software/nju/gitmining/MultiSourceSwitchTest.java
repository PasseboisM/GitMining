package software.nju.gitmining;

import static org.junit.Assert.*;
import network.data.MassiveDataSourceDefault;
import network.service.MassiveDataSource;

import org.junit.Test;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.util.ObjChannel;

public class MultiSourceSwitchTest {

	@Test
	public void test() {
		long timeStart = System.currentTimeMillis();
		MassiveDataSource source = new MassiveDataSourceDefault();
		ObjChannel<String> chan = null;
		try {
			chan = source.getRepoNames();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		
		int page = 500, realGet = 0, total = 0;
		while(chan.hasMore()) {
			try {
				realGet = chan.getObj(page).size();
			} catch (DataTransferException e) {
				e.printStackTrace();
			}
			System.out.println("Got:"+realGet);
			total += realGet;
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time Elapse:"+(timeEnd-timeStart));
		assertEquals(total,3216);
		/*
		 * Result:
		 * 4 threads : About 1000ms, between 850ms and 1250ms
		 * 1 thread  : About 1000ms, between 600ms and 1500ms
		 */
	}

}
