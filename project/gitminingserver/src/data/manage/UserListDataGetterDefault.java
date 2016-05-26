package data.manage;

import java.io.IOException;
import java.util.List;

import data.service.UserListDataGetter;
import network.service.UserRelatedDataSource;
import network.service.NetworkServiceFactory;

/**
 * 
 * @author River
 * TODO 实现Repository、User向String的转换
 */
public class UserListDataGetterDefault extends UserListDataGetter {
	
	private static UserListDataGetter instance = new UserListDataGetterDefault();
	
	private UserRelatedDataSource fromNetwork = NetworkServiceFactory.getInstance().getUserRelatedDataSource();
			
	private UserListDataGetterDefault() {}
	
	public static UserListDataGetter getInstance() {
		return instance;
	}

	@Override
	public List<String> getOwnedRepositories(String login) throws IOException {
		return null;
//		return fromNetwork.listOwnedRepositories(login);
	}

	@Override
	public List<String> getStarredRepositories(String login) throws IOException {
		return null;
//		return fromNetwork.listStarredRepositories(login);
	}

	@Override
	public List<String> getSubscrippedRepositories(String login) throws IOException {
		return null;
//		return fromNetwork.listSubscrippedRepositories(login);
	}

	@Override
	public List<String> getFollowers(String login) throws IOException {
		return null;
//		return fromNetwork.listFollowers(login);
	}

	@Override
	public List<String> getFollowings(String login) throws IOException {
		return null;
//		return fromNetwork.listFollowings(login);
	}

}
