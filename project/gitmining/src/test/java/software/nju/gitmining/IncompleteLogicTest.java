package software.nju.gitmining;

import static org.junit.Assert.*;

import java.util.List;

import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;

import org.junit.Test;

import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import common.service.RepositoryMin;

public class IncompleteLogicTest {

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
