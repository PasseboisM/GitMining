package network.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.model.ObjChannelWithBlockingQueue;
import common.model.RepositoryBeans;
import common.model.RepositoryMinBeans;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;
import network.connection.service.HTTPConnectionService;
import network.service.MassiveDataSource;

public class MassiveDataSourceDefault implements MassiveDataSource {
	/**
	 * @author wza14
	 */
	private Gson gson = new Gson(); 
	private UserApiMaker userApi = null;
	private RepoApiMaker repoApi = null;
	private HTTPConnectionService conn = null;
	
	public ObjChannel<String> getRepoNames() throws NetworkException {
		ObjChannel<String> channel = new ObjChannelWithBlockingQueue<String>();
		String url = repoApi.makeRepoNamesApi();
		String json = conn.do_get(url);
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json, listTypeType);
		
		String[] names = new String[repoLists.size()];
		channel.writeObj(repoLists.toArray(names));
		return channel;
	}

	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException, DataTransferException {
		ObjChannel<RepositoryMin> repoChannel = new ObjChannelWithBlockingQueue<RepositoryMin>();
		ObjChannel<String> namesChannel = this.getRepoNames();
		int numPerTime = 50;
		while(namesChannel.hasMore()){
			List<String> names = namesChannel.getObj(numPerTime);
			List<RepositoryMin> repositoryMins = new ArrayList<RepositoryMin>();
			
			for (String full_name : names) {
				String url = repoApi.makeRepoInfoApi(full_name);
				String json = conn.do_get(url);
				RepositoryMin repositoryMin = gson.fromJson(json, RepositoryMinBeans.class);
				repositoryMins.add(repositoryMin);
			}
			RepositoryMin[] repositoryMinsArray = new RepositoryMin[repositoryMins.size()];
			repoChannel.writeObj(repositoryMins.toArray(repositoryMinsArray));
		}
		return repoChannel;
	}

	public ObjChannel<Repository> getRepoInfo() throws NetworkException, DataTransferException {
		ObjChannel<Repository> repoChannel = new ObjChannelWithBlockingQueue<Repository>();
		ObjChannel<String> namesChannel = this.getRepoNames();
		int numPerTime = 50;
		while(namesChannel.hasMore()){
			List<String> names = namesChannel.getObj(numPerTime);
			List<Repository> repositories = new ArrayList<Repository>();
			
			for (String full_name : names) {
				String url = repoApi.makeRepoInfoApi(full_name);
				String json = conn.do_get(url);
				Repository repository = gson.fromJson(json, RepositoryBeans.class);
				repositories.add(repository);
			}
			Repository[] repositoriesArray = new Repository[repositories.size()];
			repoChannel.writeObj(repositories.toArray(repositoriesArray));
		}
		return repoChannel;
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<GitUser> getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MassiveDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.userApi = apiMaker.getUserApiMaker();
		this.repoApi = apiMaker.getRepoApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}

}
