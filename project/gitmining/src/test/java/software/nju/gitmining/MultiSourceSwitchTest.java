package software.nju.gitmining;

import static org.junit.Assert.*;

import java.util.List;

import network.data.MassiveDataSourceDefault;
import network.service.MassiveDataSource;

import org.junit.Test;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.util.ObjChannel;

/**
 * 测试MultiSourceSwitch类的功能（能否保证多Filter的同步输出）
 * @author xjh14
 * Ver: 1.0
 */
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
				List<String> l = chan.getObj(page);
				realGet = l.size();
				if(realGet>0) {
					System.out.println("Got:"+realGet+",e.g.:"+l.get(0));
				} else {
					System.out.println("Got nothing.");
				}
			} catch (DataTransferException e) {
				e.printStackTrace();
			}
			total += realGet;
		}
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time Elapse:"+(timeEnd-timeStart));
		
		//数目为当前GitMining提供仓库数（懒的现场写单线程提取了） 
		//之后测试通不过时，记得改为实时单线程获取数目，或者重新数一下数目，更换Magic Number!
		assertEquals(total,3086);
		
		/*
		 * Result:
		 * 4 threads : About 1000ms, between 850ms and 1250ms
		 * 1 thread  : About 1000ms, between 600ms and 1500ms
		 */
	}

}
