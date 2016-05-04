package network.data;

import java.io.File;
import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

public class GHSpecificDataSource{

	private GitHub gh = null;
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException, IOException {
		this.getSpecificRepo(source.getFull_name());
		return null;
	}

	public GHRepository getSpecificRepo(String fullName) throws NetworkException, DataCorruptedException, IOException {
		GHRepository repository = gh.getRepository(fullName);
		return repository;
	}

	public GHUser getSpecificUser(GitUserMin source) throws NetworkException, IOException {
		return this.getSpecificUser(source.getLogin());
	}

	public GHUser getSpecificUser(String login) throws NetworkException, IOException {
		return gh.getUser(login);
	}
	
	public GHSpecificDataSource() {
		try {
			File propertyFile = new File(System.getProperty("user.dir"), ".github");
			GitHubBuilder builder = GitHubBuilder.fromPropertyFile(propertyFile.getPath());
			this.gh = builder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
