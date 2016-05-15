package logic.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;
import data.service.DataServiceFactory;
import data.service.UserListDataGetter;
import logic.service.UserRelatedListGetter;

public class UserRelatedListGetterDefault implements UserRelatedListGetter {

	
	private UserListDataGetter getter = DataServiceFactory.getInstance().getUserListDataGetter();
	
	@Override
	public List<String> getOwnedRepositoryNames(String login) throws IOException {
		return getRepoName(getter.getOwnedRepositories(login));
	}


	@Override
	public List<String> getStarredRepositoryNames(String login) throws IOException {
		return getRepoName(getter.getStarredRepositories(login));
	}

	@Override
	public List<String> getSubscrippedRepositoryNames(String login) throws IOException {
		return getRepoName(getter.getSubscrippedRepositories(login));
	}

	@Override
	public List<String> getFollowerNames(String login) throws IOException {
		return getUserName(getter.getFollowers(login));
	}

	@Override
	public List<String> getFollowingNames(String login) throws IOException {
		return getUserName(getter.getFollowings(login));
	}

	private List<String> getRepoName(List<Repository> repositories) {
		List<String> result = new ArrayList<>();
		repositories.forEach(repo->{
			result.add(repo.getFull_name());
		});
		return result;
	}
	
	private List<String> getUserName(List<GitUser> users) {
		List<String> result = new ArrayList<>();
		users.forEach(user->{
			result.add(user.getLogin());
		});
		return result;
	}
}
