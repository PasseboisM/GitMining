package data.service;

import java.io.IOException;
import java.util.List;

import data.manage.UserListDataGetterDefault;

public abstract class UserListDataGetter {

	public abstract List<String> getOwnedRepositories(String login) throws IOException;
	public abstract List<String> getStarredRepositories(String login) throws IOException;
	public abstract List<String> getSubscrippedRepositories(String login) throws IOException;
	public abstract List<String> getFollowers(String login) throws IOException;
	public abstract List<String> getFollowings(String login) throws IOException;
	public static UserListDataGetter getInstance() {
		return UserListDataGetterDefault.getInstance();
	}
}
