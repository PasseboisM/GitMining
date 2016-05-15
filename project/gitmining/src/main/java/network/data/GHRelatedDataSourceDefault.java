package network.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import common.model.HyberRepository;
import common.model.HyberUser;
import common.service.GitUser;
import common.service.Repository;
import network.service.GHRelatedDataSource;

public class GHRelatedDataSourceDefault implements GHRelatedDataSource {

	
	private GitHub gh = null;
	
	
	@Override
	public List<Repository> listOwnedRepositories(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listRepository(ghUser.listRepositories());
	}
	
	@Override
	public List<Repository> listStarredRepositories(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listRepository(ghUser.listStarredRepositories());
	}
	
	@Override
	public List<Repository> listSubscrippedRepositories(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listRepository(ghUser.listSubscriptions());
	}
	
	@Override
	public List<GitUser> listFollowers(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listUsers(ghUser.listFollows());
	}
	
	@Override
	public List<GitUser> listFollowings(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listUsers(ghUser.listFollows());
	}

	@Override
	public List<GitUser> listContributors(String fullName) throws IOException {
		GHRepository ghRepository = gh.getRepository(fullName);
		return listUsers(ghRepository.listContributors());
	}

	@Override
	public List<GitUser> listCollaborators(String fullName) throws IOException {
		GHRepository ghRepository = gh.getRepository(fullName);
		return listUsers(ghRepository.listCollaborators());
	}

	private <T extends GHUser> List<GitUser> listUsers(PagedIterable<T> pagedIterable) {
		List<GitUser> result = new ArrayList<>();
		pagedIterable.forEach(user->{
			GitUser hyberUser =  new HyberUser(user);
			result.add(hyberUser);
		});
		return result;
	}

	private List<Repository> listRepository(PagedIterable<GHRepository> pagedIterable) {
		List<Repository> result = new ArrayList<>();
		pagedIterable.forEach(repository->{
			Repository hyberRepo = new HyberRepository(repository);
			result.add(hyberRepo);
		});
		return result;
	}
	
	public GHRelatedDataSourceDefault() {
		this.gh = GHNetworkServiceFactory.getGitHub();
	}

}
