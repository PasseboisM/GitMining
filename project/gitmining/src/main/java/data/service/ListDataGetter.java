package data.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;
import data.manage.ListDataGetterDefault;

public abstract class ListDataGetter {

	public abstract List<Repository> getOwnedRepositories(String login) throws IOException;
	public abstract List<Repository> getStarredRepositories(String login) throws IOException;
	public abstract List<Repository> getSubscrippedRepositories(String login) throws IOException;
	public abstract List<GitUser> getFollowers(String login) throws IOException;
	public abstract List<GitUser> getFollowings(String login) throws IOException;
	public abstract List<GitUser> getContributors(String fullName) throws IOException;
	public abstract List<GitUser> getCollaborators(String fullName) throws IOException;
	public static ListDataGetter getInstance() {
		return ListDataGetterDefault.getInstance();
	}
}
