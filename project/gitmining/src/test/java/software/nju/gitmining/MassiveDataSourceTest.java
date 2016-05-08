package software.nju.gitmining;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.kohsuke.github.GHRepository;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import network.data.GHMassiveDataSourse;
import network.data.MassiveDataSourceDefault;
import network.service.MassiveDataSource;

/**
 * 测试网络层获取数据Channel的功能
 * @author xjh14
 * Ver: 1.0
 */
public class MassiveDataSourceTest {
	
	@Test
	public void ghrepoTest() throws DataTransferException, NetworkException, IOException{
		GHMassiveDataSourse source = new GHMassiveDataSourse();
		ObjChannel<GHRepository> repoChan = source.getRepo();
		int total = 0;
		while(repoChan.hasMore()){
			List<GHRepository> repositories = repoChan.getObj(100);
			System.out.println("Got:"+repositories.size());
			total+=repositories.size();
			if (repositories.size()>0) {
				System.out.println(repositories.get(0).getFullName());
				System.out.println();
			}
		}
		System.out.println(total);
	}

	//@Test
	public void repoTest() throws NetworkException, DataTransferException {
		MassiveDataSource source = new MassiveDataSourceDefault();
		
		ObjChannel<RepositoryMin> repoChan = source.getRepoMinInfo();
		
		int total = 0;
		
		while(repoChan.hasMore()) {
			List<RepositoryMin> repos = repoChan.getObj(100);
			System.out.println("Got:"+repos.size()); 
			total += repos.size();
			if(repos.size()>0) {
				printRepo(repos.get(0));
			}
			System.out.println();
		}
		
//		assertEquals(3086,total);
		
		/**
		 * Test result records:
		 * 
		 * 
		 * 环境：机房网（人不很多）
		 * (4 threads in each channel)failed: 77s running time,expected 3216, but was 3160
		 * 漏掉了一些数据，同步方面有问题
		 * (1 thread in each channel)succeeded: 194s running time
		 * 结论：如果是不用Channel的真单线程的话，恐怕时间甚至会不止250s，
		 * 		所以多线程还是要有，一定要找出同步性有问题的地方
		 * 
		 * 环境：宿舍渣网
		 * success, 77s (16 threads in each channel)
		 * success, 94s (8 threads in each channel)
		 * success, 183s(4 threads in each channel)
		 * success, 361s(2 threads in each channel)
		 * success, 812s(1 thread  in each channel)
		 * 结论：
		 * 为了实际运行时避免线程过度竞争，最终决定采用线程数目为processors * 2(本次测试中的8 threads) 
		 */
	}
	
//	@Test
	public void userTest() throws NetworkException, DataTransferException {
		MassiveDataSource source = new MassiveDataSourceDefault();
		
		ObjChannel<GitUser> repoChan = source.getUserInfo();
		
		int total = 0;
		
		while(repoChan.hasMore()) {
			List<GitUser> repos = repoChan.getObj(50);
			System.out.println("Got:"+repos.size()); 
			total += repos.size();
			if(repos.size()>0) {
				printUser(repos.get(0));
			}
			System.out.println();
		}
		
//		assertEquals(3086,total);
	}

	private void printUser(GitUser gitUser) {
		System.out.println("Login"+gitUser.getLogin());
		System.out.println("Location:"+gitUser.getLocation());
		System.out.println("Mail:"+gitUser.getEmail());
		
	}

	public void printRepo(RepositoryMin r) {
		System.out.println("Name:"+r.getFull_name());
		System.out.println("ID:"+r.getId());


	}
}
