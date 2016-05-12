package software.nju.gitmining;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

public class GHTester {
	@Test
	public void ghTester() throws IOException {
		File propertyFile = new File(System.getProperty("user.dir"), "github.dll");
		GitHubBuilder builder = GitHubBuilder.fromPropertyFile(propertyFile.getPath());
		GitHub github = builder.build();
		System.out.println(github.getRateLimit().remaining);
		for (int i = 0; i < 1; i++) {
			System.out.println("time for "+(i+1));
			search(github,i);
		}
	}

	private void search(GitHub github,int time) {
		GHUserSearchBuilder searchBuilder = github.searchUsers();
		System.out.println(searchBuilder.q("AB"+time).q("0").list().asList().size());
	}
}
