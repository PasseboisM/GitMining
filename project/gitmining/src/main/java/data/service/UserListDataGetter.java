package data.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;
import data.manage.UserListDataGetterDefault;

public abstract class UserListDataGetter {

	public abstract List<Repository> getOwnedRepositories(String login) throws IOException;
	public abstract List<Repository> getStarredRepositories(String login) throws IOException;
	public abstract List<Repository> getSubscrippedRepositories(String login) throws IOException;
	public abstract List<GitUser> getFollowers(String login) throws IOException;
	public abstract List<GitUser> getFollowings(String login) throws IOException;
	public static UserListDataGetter getInstance() {
		return UserListDataGetterDefault.getInstance();
	}
}
