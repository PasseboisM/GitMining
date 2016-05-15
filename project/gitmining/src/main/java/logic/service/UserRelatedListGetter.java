package logic.service;

import java.io.IOException;
import java.util.List;

public interface UserRelatedListGetter {
	public List<String> getOwnedRepositoryNames(String login) throws IOException;
	public List<String> getStarredRepositoryNames(String login) throws IOException;
	public List<String> getSubscrippedRepositoryNames(String login) throws IOException;
	public List<String> getFollowerNames(String login) throws IOException;
	public List<String> getFollowingNames(String login) throws IOException;
}
