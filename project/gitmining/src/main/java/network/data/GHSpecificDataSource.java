package network.data;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.model.HyberRepository;
import common.model.HyberUser;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import network.service.SpecificDataSource;

public class GHSpecificDataSource implements SpecificDataSource{

	private GitHub gh = null;
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException {
		return this.getSpecificRepo(source.getFull_name());
	}

	public Repository getSpecificRepo(String fullName) throws NetworkException, DataCorruptedException {
		GHRepository repository = null;
		Repository hyberRepo = null;
		try {
			repository = gh.getRepository(fullName);
			hyberRepo = new HyberRepository(repository);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			repository.listLabels().forEach(label->{
				System.out.println(label.getName());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hyberRepo;
	}

	public GitUser getSpecificUser(GitUserMin source) throws NetworkException {
		return this.getSpecificUser(source.getLogin());
	}

	public GitUser getSpecificUser(String login) throws NetworkException {
		GHUser ghUser = null;
		GitUser hyberUser = null;
		try {
			ghUser = gh.getUser(login);
			hyberUser =  new HyberUser(ghUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hyberUser;
	}
	
	
	public GHSpecificDataSource() {
		this.gh = GHNetworkServiceFactory.getGitHub();
	}
}
