package network.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import com.google.gson.Gson;

import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.NetworkException;
import common.model.HyberRepository;
import common.model.HyberUser;
import common.model.beans.GitUserBeans;
import common.model.beans.GitUserMinBeans;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.SearchApiMaker;
import network.api.service.UserApiMaker;
import network.connection.service.HTTPConnectionService;
import network.service.GHDataSource;

public class GHDataSourceDefault implements GHDataSource {

	private Gson gson = new Gson(); 
	private SearchApiMaker searchApi = null;
	private HTTPConnectionService conn = null;
	
	private GitHub gh;

	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam) {
		GHRepositorySearchBuilder builder = gh.searchRepositories();
		System.out.println(builder);
		Language[] langs = repositorySearchParam.getLangs();
		String[] keywords = repositorySearchParam.getKeywords();
		RepoSortStadard sortStandard = repositorySearchParam.getSortStandard();
		for (String string : keywords) {
			builder.q(string);
		}
		System.out.println("keywords search done");
		for (Language language : langs) {
			builder.language(language.getName());
		}
		System.out.println("language search done");
		builder.sort(sortStandard.getSort());
		System.out.println("sort done");
		
		long time1 = System.currentTimeMillis();
		System.out.println(builder.list().asList());
		System.out.println("Time used:"+(System.currentTimeMillis()-time1)+"ms");
				
		
		List<Repository> repositories = new ArrayList<>();
		builder.list().asList().forEach((GHRepository ghRepo)->{
			repositories.add(new HyberRepository(ghRepo));
		});
		return repositories;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam userSearchParam) throws NetworkException {
		int page=1;
		int totalCount=0;
		List<GitUser> users = new ArrayList<>();
		do{
		String url = searchApi.makeSearchUserApi(userSearchParam,page);
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
		}while(page*30<totalCount);
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
		this.gh = GHNetworkServiceFactory.getGitHub();
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.searchApi = apiMaker.getSearchApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}

}
