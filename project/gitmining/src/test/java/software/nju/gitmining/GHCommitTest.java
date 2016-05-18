package software.nju.gitmining;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import network.data.UserRelatedDataSourceDefault;
import network.service.UserRelatedDataSource;

public class GHCommitTest {

	@Test
	public void test() throws NetworkException, DataCorruptedException, IOException {
//		SpecificDataSource source = new GHSpecificDataSource();
//		source.getSpecificRepo("rubinius/rubinius");
//		System.out.println();
//		source.getSpecificRepo("facebook/fbctf");
//		GHTAnalysisDataSourceDefault source = new GHTAnalysisDataSourceDefault();
//		source.recommendUsers(Language.C_SHARP);
		
//		RepoRelatedDataSource source2 = new RepoRelatedDataSourceDefault();
//		List<GitUser> users2 = source2.listCollaborators("rubinius/rubinius");
		
		UserRelatedDataSource source = new UserRelatedDataSourceDefault();
//		List<GitUser> users = source.listFollowings("ShiAnni");
//		System.out.println(users.size());
//		users = source.listFollowers("rubinius");
//		System.out.println(users.size());
		List<Repository> repositories = source.listOwnedRepositories("BryanCunningham");
		
		for (Repository repository : repositories) {
			System.out.println(repository.getFull_name());
		}
		
	}

}
