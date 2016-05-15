package network.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;

public interface UserRelatedDataSource {
	public List<Repository> listOwnedRepositories(String login) throws IOException;
	public List<Repository> listStarredRepositories(String login) throws IOException;
	public List<Repository> listSubscrippedRepositories(String login) throws IOException;
	public List<GitUser> listFollowers(String login) throws IOException;
	public List<GitUser> listFollowings(String login) throws IOException;
}
