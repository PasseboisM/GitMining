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
import network.service.UserRelatedDataSource;

public class UserRelatedDataSourceDefault implements UserRelatedDataSource {

	
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
		return listUsers(ghUser.listFollowers());
	}
	
	@Override
	public List<GitUser> listFollowings(String login) throws IOException {
		GHUser ghUser = gh.getUser(login);
		return listUsers(ghUser.listFollows());
	}

	private List<GitUser> listUsers(PagedIterable<GHUser> pagedIterable) {
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
	
	public UserRelatedDataSourceDefault() {
		this.gh = GHNetworkServiceFactory.getGitHub();
	}

}
