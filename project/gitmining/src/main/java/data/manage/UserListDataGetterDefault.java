package data.manage;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;
import data.service.UserListDataGetter;
import network.service.UserRelatedDataSource;
import network.service.NetworkServiceFactory;

public class UserListDataGetterDefault extends UserListDataGetter {
	
	private static UserListDataGetter instance = new UserListDataGetterDefault();
	
	private UserRelatedDataSource fromNetwork = NetworkServiceFactory.getInstance().getUserRelatedDataSource();
			
	private UserListDataGetterDefault() {}
	
	public static UserListDataGetter getInstance() {
		return instance;
	}

	@Override
	public List<Repository> getOwnedRepositories(String login) throws IOException {
		return fromNetwork.listOwnedRepositories(login);
	}

	@Override
	public List<Repository> getStarredRepositories(String login) throws IOException {
		return fromNetwork.listStarredRepositories(login);
	}

	@Override
	public List<Repository> getSubscrippedRepositories(String login) throws IOException {
		return fromNetwork.listSubscrippedRepositories(login);
	}

	@Override
	public List<GitUser> getFollowers(String login) throws IOException {
		return fromNetwork.listFollowers(login);
	}

	@Override
	public List<GitUser> getFollowings(String login) throws IOException {
		return fromNetwork.listFollowings(login);
	}

}
