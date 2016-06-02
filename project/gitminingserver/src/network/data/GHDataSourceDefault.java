package network.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.google.gson.Gson;

import common.exception.NetworkException;
import common.model.beans.GitUserBeans;
import common.model.beans.RepositoryBeans;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;
import network.api.service.ApiMakerService;
import network.api.service.SearchApiMaker;
import network.connection.service.HTTPConnectionService;
import network.service.GHDataSource;

public class GHDataSourceDefault implements GHDataSource {

	private Gson gson = new Gson(); 
	private SearchApiMaker searchApi = null;
	private HTTPConnectionService conn = null;
	private static final int ITEM_PER_PAGE = 30;
	private static final int PAGE_AMOUNT_LIMIT = 1;
	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam) throws NetworkException {
		int page=1;
		int totalCount=0;
		List<Repository> repos = new ArrayList<>();
		do{
			String url = searchApi.makeSearchRepoApi(repositorySearchParam, page);
			if(url==null)	throw new NetworkException();
			String json = conn.do_get(url);
			System.out.println("page"+page+":");
			JSONObject jsonObject = new JSONObject(json);
			try {
				totalCount = jsonObject.getInt("total_count");
			} catch (JSONException e) {
				System.out.println("sleep for 40 seconds");
				try {
					Thread.sleep(1000*40);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("wake up and continue downloading");
				continue;
			}
			JSONArray jsonArray = jsonObject.getJSONArray("items");
			jsonArray.forEach((Object object)->{
				String user = ((JSONObject)object).toString();
				Repository result = gson.fromJson(user, RepositoryBeans.class);
				repos.add(result);
			});
			page++;
			}while(page*ITEM_PER_PAGE<totalCount&&page<=PAGE_AMOUNT_LIMIT);
			return repos;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam userSearchParam) throws NetworkException {
		int page=1;
		int totalCount=0;
		List<GitUser> users = new ArrayList<>();
		do{
		String url = searchApi.makeSearchUserApi(userSearchParam,page);
		if(url==null)	throw new NetworkException();
		String json = conn.do_get(url);
		System.out.println("page"+page+":");
		JSONObject jsonObject = new JSONObject(json);
		try {
			totalCount = jsonObject.getInt("total_count");
		} catch (JSONException e) {
			System.out.println("sleep for 40 seconds");
			try {
				Thread.sleep(1000*40);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("wake up and continue downloading");
			continue;
		}
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		jsonArray.forEach((Object object)->{
			String user = ((JSONObject)object).toString();
			GitUser result = gson.fromJson(user, GitUserBeans.class);
			users.add(result);
		});
		page++;
		}while(page*ITEM_PER_PAGE<totalCount&&page<=PAGE_AMOUNT_LIMIT);
		return users;
	}

	@Override
	public boolean isCredentialValid(String login, String password) throws NetworkException {
		Properties properties = new Properties();
		properties.setProperty("login", login);
		properties.setProperty("password", password);
		GitHubBuilder builder = GitHubBuilder.fromProperties(properties);
		boolean isCredentialValid = false;
		try {
			GitHub github = builder.build();
			isCredentialValid = github.isCredentialValid();
		} catch (Exception e) {
			throw new NetworkException();
		}
		return isCredentialValid;
	}
	
	public GHDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.searchApi = apiMaker.getSearchApiMaker();
		this.conn = HTTPConnectionService.getInstance();
		
	}


}
