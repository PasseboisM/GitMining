package network.data;

import java.lang.reflect.Type;
import java.util.LinkedList;
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
import common.util.DataSourceConsumer;
import common.util.DataSourceProducer;
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
//		System.out.println(url);
		String json = conn.do_get(url);
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json, listTypeType);
		
		DataSourceProducer<String> producer = new DataSourceProducer<String>(channel, repoLists);
		Thread dataProduceThread = new Thread(producer);
		dataProduceThread.start();
		return channel;
	}

	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException, DataTransferException {
		ObjChannel<String> namesChannel = this.getRepoNames();
		DataSourceConsumer<String> consumer = new DataSourceConsumer<String>(namesChannel);
		ObjChannel<RepositoryMin> repoChannel = new ObjChannelWithBlockingQueue<RepositoryMin>();
		List<RepositoryMin> repositoryList = new LinkedList<RepositoryMin>();
		
		List<String> namesList = consumer.getData();
		
		for (String full_name : namesList) {
			String url = repoApi.makeRepoInfoApi(full_name);
			String json = conn.do_get(url);
			RepositoryMin repositoryMin = gson.fromJson(json, RepositoryMinBeans.class);
			repositoryList.add(repositoryMin);
		}
		DataSourceProducer<RepositoryMin> producer = new DataSourceProducer<RepositoryMin>(repoChannel, repositoryList);
		Thread dataProduceThread = new Thread(producer);
		dataProduceThread.start();
		return repoChannel;
	}

	public ObjChannel<Repository> getRepoInfo() throws NetworkException, DataTransferException {
		ObjChannel<String> namesChannel = this.getRepoNames();
		DataSourceConsumer<String> consumer = new DataSourceConsumer<String>(namesChannel);
		ObjChannel<Repository> repoChannel = new ObjChannelWithBlockingQueue<Repository>();
		List<Repository> repositoryList = new LinkedList<Repository>();
		
		List<String> namesList = consumer.getData();
		
		for (String full_name : namesList) {
			String url = repoApi.makeRepoInfoApi(full_name);
			String json = conn.do_get(url);
			Repository repository = gson.fromJson(json, RepositoryBeans.class);
			repositoryList.add(repository);
		}
		DataSourceProducer<Repository> producer = new DataSourceProducer<Repository>(repoChannel, repositoryList);
		Thread dataProduceThread = new Thread(producer);
		dataProduceThread.start();
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
