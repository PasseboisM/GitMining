package network.data;

import java.io.File;
import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import network.service.SpecificDataSource;

public class GHSpecificDataSource implements SpecificDataSource{

	private GitHub gh = null;
	@Override
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException {
		this.getSpecificRepo(source.getFull_name());
		return null;
	}

	@Override
	public Repository getSpecificRepo(String fullName) throws NetworkException, DataCorruptedException {
		try {
			GHRepository repository = gh.getRepository(fullName);
			System.out.println("fullname : "+repository.getFullName()+"\nname : "+repository.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GitUser getSpecificUser(GitUserMin source) throws NetworkException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getSpecificUser(String login) throws NetworkException {
		// TODO Auto-generated method stub
		return null;
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
