package software.nju.gitmining;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;
import network.data.SpecificDataSourceDefault;
import network.service.SpecificDataSource;

/**
 * 测试网络层获取特定数据对象的功能
 * @author xjh14
 * Ver: 1.0
 */
public class SpecificDataSourceTest {

	@Test
	public void test() throws NetworkException, DataCorruptedException {
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
