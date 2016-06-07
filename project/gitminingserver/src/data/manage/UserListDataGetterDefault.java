package data.manage;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import data.service.UserListDataGetter;
import static common.util.JSONHelper.toJSONList;
import network.service.UserRelatedDataSource;
import network.service.NetworkServiceFactory;

/**
 * @author River
 */
public class UserListDataGetterDefault extends UserListDataGetter {
	
	private static UserListDataGetter instance = new UserListDataGetterDefault();
	
	private UserRelatedDataSource fromNetwork = NetworkServiceFactory.getInstance().getUserRelatedDataSource();
	private Gson gson = new Gson();
	
	private UserListDataGetterDefault() {}
	
	public static UserListDataGetter getInstance() {
		return instance;
	}

	@Override
	public List<String> getOwnedRepositories(String login) throws IOException {
		return toJSONList(fromNetwork.listOwnedRepositories(login));
	}

	@Override
	public List<String> getStarredRepositories(String login) throws IOException {
		return toJSONList(fromNetwork.listStarredRepositories(login));
	}

	@Override
	public List<String> getSubscrippedRepositories(String login) throws IOException {
		return toJSONList(fromNetwork.listSubscrippedRepositories(login));
	}

	@Override
	public List<String> getFollowers(String login) throws IOException {
		return toJSONList(fromNetwork.listFollowers(login));
	}

	@Override
	public List<String> getFollowings(String login) throws IOException {
		return toJSONList(fromNetwork.listFollowings(login));
	}

}
