package software.nju.gitmining;

import static org.junit.Assert.*;

import java.util.List;

import network.data.MassiveDataSourceDefault;
import network.service.MassiveDataSource;

import org.junit.Test;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.service.Repository;
import common.util.ObjChannel;

public class MassiveDataSourceTest {

	@Test
	public void test() throws NetworkException, DataTransferException {
		MassiveDataSource source = new MassiveDataSourceDefault();
		
		ObjChannel<Repository> repoChan = source.getRepoInfo();
		
		int total = 0;
		
		while(repoChan.hasMore()) {
			List<Repository> repos = repoChan.getObj(100);
			System.out.println("Got:"+repos.size()); 
			total += repos.size();
			if(repos.size()>0) {
				printRepo(repos.get(0));
			}
			System.out.println();
		}
		
		assertEquals(3216,total);
		
		/**
		 * Test result records:
		 * 
		 * (4 threads in each channel)failed: 77s running time,expected 3216, but was 3160
		 * 漏掉了一些数据，同步方面有问题
		 * 
		 * (1 thread in each channel)succeeded: 194s running time
		 * 结论：如果是不用Channel的真单线程的话，恐怕时间甚至会不止250s，
		 * 		所以多线程还是要有，一定要找出同步性有问题的地方
		 * 
		 */
	}

	public void printRepo(Repository r) {
		System.out.println("Name:"+r.getFull_name());
		System.out.println("ID:"+r.getId());
		System.out.println("URL:"+r.getArchive_url());
		System.out.println("Created at:"+r.getCreated_at());

	}
}
