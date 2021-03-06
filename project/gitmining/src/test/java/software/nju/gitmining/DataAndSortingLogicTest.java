package software.nju.gitmining;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;

/**
 * 作为集成测试，同时测试逻辑层数据获取与排序功能
 * @author xjh14
 * Ver: 1.0
 */
public class DataAndSortingLogicTest {

	@Test
	public void test() throws NetworkException, DataCorruptedException {
		GeneralGetter getter = LogicServiceFactory.getInstance().getGeneralGetter();

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Passed 20 secs\n");
		int got = getter.getNumOfRepositories();
		System.out.println("Num of repos got:"+got);
		
		int lastStars = Integer.MAX_VALUE, thisStars = 0;
		if (got>0) {
			List<Repository> result = getter.getRepositories(1, 50, RepoSortStadard.STARS_DESCENDING);
			for(Repository r:result) {
				thisStars = r.getStargazers_count();
				assertEquals(thisStars<=lastStars,true);
				printRepo(r);
				lastStars = r.getStargazers_count();
			}
		}
		
		/**
		 * Result: success (Running time=32.541s)
		 */
		
	}

	
	public static void printRepo(Repository r) {
		System.out.println("Name:"+r.getFull_name());
		System.out.println("ID:"+r.getId());
		System.out.println("Stars:"+r.getStargazers_count());
		System.out.println();
	}
	
}
