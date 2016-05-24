package network.data;

import java.io.File;
import java.io.IOException;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import network.service.AnalysisDataSource;
import network.service.GHDataSource;
import network.service.MassiveDataSource;
import network.service.NetworkConnectionTester;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;
import network.service.UserRelatedDataSource;

public class GHNetworkServiceFactory extends NetworkServiceFactory {

	@Override
	public SpecificDataSource getSpecificDataSource() {
		return new GHSpecificDataSource();
	}

	@Override
	@Deprecated
	public MassiveDataSource getMassiveDataSource() {
		return new MassiveDataSourceDefault();
	}

	@Override
	public NetworkConnectionTester getNetworkConnectionTester() {
		return new NetworkConnectionTesterDefault();
	}
	
	@Override
	public UserRelatedDataSource getUserRelatedDataSource() {
		return new UserRelatedDataSourceDefault();
	}
	
	/*@Override
	public RepoRelatedDataSource getRepoRelatedDataSource() {
		return new RepoRelatedDataSourceDefault();
	}*/
	
	@Override
	public GHDataSource getGHDataSource() {
		return new GHDataSourceDefault();
	}
	
	@Override
	public AnalysisDataSource getAnalysisDataSource() {
		return new GHTAnalysisDataSourceDefault();
	}
	
	private static GitHub github = null;
	public static GitHub getGitHub(){
		if (github==null) {
			try {
				File propertyFile = new File(System.getProperty("user.dir"), "github.dll");
				GitHubBuilder builder = GitHubBuilder.fromPropertyFile(propertyFile.getPath());
				github = builder.build();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return github;
	}

	



}
