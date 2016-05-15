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
	public List<Repository> getOwnedRepositoryNames(String login) throws IOException {
		return getter.getOwnedRepositories(login);
	}


	@Override
	public List<Repository> getStarredRepositoryNames(String login) throws IOException {
		return getter.getStarredRepositories(login);
	}

	@Override
	public List<Repository> getSubscrippedRepositoryNames(String login) throws IOException {
		return getter.getSubscrippedRepositories(login);
	}

	@Override
	public List<GitUser> getFollowerNames(String login) throws IOException {
		return getter.getFollowers(login);
	}

	@Override
	public List<GitUser> getFollowingNames(String login) throws IOException {
		return getter.getFollowings(login);
	}

}
