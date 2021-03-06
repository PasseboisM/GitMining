package software.nju.gitmining;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;
import network.data.GHDataSourceDefault;

public class GHDataSourceTest {

	@Test
	public void repoSearchTest() throws NetworkException {
		GHDataSourceDefault source = new GHDataSourceDefault();
		Language langs[] = {Language.C};
		Category[] cates = {};
		String keywords[] = {"game","游戏"};
		RepositorySearchParam repositorySearchParam = new RepositorySearchParam(langs, cates, keywords, RepoSortStadard.NO_SORT);
		List<Repository> repositories = source.searchRepository(repositorySearchParam);
		System.out.println(repositories.size());
	}
	@Test
	public void userSearchTest() throws NetworkException {
		GHDataSourceDefault source = new GHDataSourceDefault();
		String loginName = "xriver";
		UserSearchParam userSearchParam = new UserSearchParam(loginName, UserSortSandard.NO_SORT);
		List<GitUser> users = source.searchUser(userSearchParam);
		System.out.println(users.size());
	}
	
	@Test
	public void loginTest() throws NetworkException {
		GHDataSourceDefault source = new GHDataSourceDefault();
		assertEquals(true, source.isCredentialValid("ShiAnni", "sa19951229"));
		assertEquals(false, source.isCredentialValid("ShiAnni", "sa1995129"));
	}

}
