package network.data;

import java.io.File;
import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import network.service.MassiveDataSource;
import network.service.NetworkConnectionTester;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class GHNetworkServiceFactory extends NetworkServiceFactory {

	@Override
	public SpecificDataSource getSpecificDataSource() {
		return new GHSpecificDataSource();
	}

	@Override
	public MassiveDataSource getMassiveDataSource() {
		return new MassiveDataSourceDefault();
	}

	@Override
	public NetworkConnectionTester getNetworkConnectionTester() {
		return new NetworkConnectionTesterDefault();
	}
	
	private static GitHub github = null;
	public static GitHub getGitHub(){
		if (github==null) {
			try {
				File propertyFile = new File(System.getProperty("user.dir"), ".github");
				GitHubBuilder builder = GitHubBuilder.fromPropertyFile(propertyFile.getPath());
				github = builder.build();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return github;
	}

}
