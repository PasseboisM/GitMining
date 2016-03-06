package network.data;

import com.google.gson.Gson;

import common.exception.NetworkException;
import common.model.GitUserBeans;
import common.model.RepositoryBeans;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;
import network.connection.service.HTTPConnectionService;
import network.service.SpecificDataSource;

public class SpecificDataSourceDefault implements SpecificDataSource {

	private Gson gson = new Gson(); 
	private UserApiMaker userApi = null;
	private RepoApiMaker repoApi = null;
	private HTTPConnectionService conn = null;
	
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException {
		return getSpecificRepo(source.getFull_name());
	}

	public Repository getSpecificRepo(String fullName) throws NetworkException {
		
		String url = repoApi.makeRepoInfoApi(fullName);
		String json = conn.do_get(url);
		Repository result = gson.fromJson(json, RepositoryBeans.class);
		
		return result;
	}

	public GitUser getSpecificUser(GitUserMin source) throws NetworkException {
		return getSpecificUser(source.getLogin());
	}

	public GitUser getSpecificUser(String login) throws NetworkException {

		String url = userApi.makeUserAPI(login);
		String json = conn.do_get(url);
		GitUser result = gson.fromJson(json, GitUserBeans.class);
		
		return result;
	}

	public SpecificDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.userApi = apiMaker.getUserApiMaker();
		this.repoApi = apiMaker.getRepoApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}
}
