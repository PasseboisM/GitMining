package logic.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;

public interface UserRelatedListGetter {
	public List<Repository> getOwnedRepositoryNames(String login) throws IOException;
	public List<Repository> getStarredRepositoryNames(String login) throws IOException;
	public List<Repository> getSubscrippedRepositoryNames(String login) throws IOException;
	public List<GitUser> getFollowerNames(String login) throws IOException;
	public List<GitUser> getFollowingNames(String login) throws IOException;
}
