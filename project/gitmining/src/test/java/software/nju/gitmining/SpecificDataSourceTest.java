package software.nju.gitmining;

import static org.junit.Assert.*;
import network.data.SpecificDataSourceDefault;
import network.service.SpecificDataSource;

import org.junit.Test;

import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;

public class SpecificDataSourceTest {

	@Test
	public void test() throws NetworkException {
		SpecificDataSource source = new SpecificDataSourceDefault();
		GitUser user = source.getSpecificUser("rubinius");
		Repository repo = source.getSpecificRepo("rubinius/rubinius");
		printRepo(repo);
		printUser(user);
		assertEquals(user.getLogin(),"rubinius");
		assertEquals(repo.getFull_name(),"rubinius/rubinius");
	}

	public void printRepo(Repository r) {
		System.out.println("Name:"+r.getFull_name());
		System.out.println("ID:"+r.getId());
		System.out.println("URL:"+r.getArchive_url());
		System.out.println("Created at:"+r.getCreated_at());
		System.out.println();
	}
	
	public void printUser(GitUser u) {
		System.out.println("Login:"+u.getLogin());
		System.out.println("Location:"+u.getLocation());
		System.out.println("AvatarUrl:"+u.getAvatar_url()+" , nice photo!");
		System.out.println("Followers_count:"+u.getFollowers());
		System.out.println();
	}
	
}
