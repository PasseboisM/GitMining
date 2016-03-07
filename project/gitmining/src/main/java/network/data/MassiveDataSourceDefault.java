package network.data;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.event.ListSelectionEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.model.BasicSourceSwitch;
import common.model.ObjChannelWithBlockingQueue;
import common.model.RepositoryBeans;
import common.model.RepositoryMinBeans;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import common.util.Writable;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;
import network.connection.service.HTTPConnectionService;
import network.service.MassiveDataSource;

/**
 * 包含了JSON字符串解析功能的数据源，为上层提供能够获取批量信息的ObjChannel
 * 为了提高运行效率，在JSON解析、信息传输的行为上都采用了多线程操作
 * @author xjh14
 *
 */
public class MassiveDataSourceDefault implements MassiveDataSource {
	/**
	 * @author wza14
	 */
	private Gson gson = new Gson(); 
	private UserApiMaker userApi = null;
	private RepoApiMaker repoApi = null;
	private HTTPConnectionService conn = null;
	
	private static final int SUGGESTED_THREAD_NUM = Integer.parseInt(System.getenv().get("NUMBER_OF_PROCESSORS"));
//	private static final int SUGGESTED_THREAD_NUM = 1;
	public ObjChannel<String> getRepoNames() throws NetworkException {
		
		ObjChannel<String> channel = new ObjChannelWithBlockingQueue<String>();
		MultiSourceSwitch<String> sourceSwitch = new BasicSourceSwitch<String>(channel);
		String url = repoApi.makeRepoNamesApi();
		
		String json = conn.do_get(url);
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json, listTypeType);
		
		List<String>[] listSplit = splitList(repoLists, SUGGESTED_THREAD_NUM);
		
		Executor exec = Executors.newCachedThreadPool();
		PureDataTransSource[] sources = new PureDataTransSource[SUGGESTED_THREAD_NUM];
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			sources[i] = new PureDataTransSource(listSplit[i], sourceSwitch);
		}
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			exec.execute(sources[i]);
		}
		
		return channel;
	}
	
	/**
	 * 自行注册、解注册的纯数据传送源
	 * 可能需要提出为非内部类
	 * @author xjh14
	 * @param <T>
	 */
	class PureDataTransSource<T> implements Runnable {

		List<T> data;
		Writable<T> target;
		
		ObjChannel<T> chan;
		MultiSourceSwitch<T> sourceSwitch;
		boolean isSwitch;
		
		PureDataTransSource(List<T> data,ObjChannel<T> chan) {
			this.data = data;
			target = chan;
			
			isSwitch = false;
			this.chan = chan;
		}
		
		PureDataTransSource(List<T> data,MultiSourceSwitch<T> sourceSwitch) {
			this.data = data;
			target = sourceSwitch;
			
			this.isSwitch = true;
			sourceSwitch.register(this);
			this.sourceSwitch = sourceSwitch;
		}
		
		public void run() {
			target.writeObj(data);
			close();
		}
		
		private void close() {
			if(isSwitch) {
				sourceSwitch.deregister(this);
			} else {
				chan.close();
			}
		}
	}
	

	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException, DataTransferException {
		ObjChannel<String> namesChannel = this.getRepoNames();
		
		ObjChannel<RepositoryMin> repoChannel = new ObjChannelWithBlockingQueue<RepositoryMin>();
		List<RepositoryMin> repositoryList = new LinkedList<RepositoryMin>();
		
		
		return repoChannel;
	}

	public ObjChannel<Repository> getRepoInfo() throws NetworkException, DataTransferException {
		ObjChannel<String> namesChannel = this.getRepoNames();
		
		ObjChannel<Repository> repoChannel = new ObjChannelWithBlockingQueue<Repository>();
		List<Repository> repositoryList = new LinkedList<Repository>();
		
		//TODO
		
		
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
	
	
	private <T> List[] splitList(List<T> origin, int pieces) {
		assert pieces > 0;
		
		List<T>[] result = new List[pieces];
		int totalLength = origin.size();
		int eachLength = totalLength / pieces;
		
		for(int i=0;i<pieces;i++) {
			
			if(i==(pieces-1)) {
				result[i] = origin.subList(i * eachLength, totalLength);
			} else {
				result[i] = origin.subList(i * eachLength, (i+1) * eachLength);
			}
		}
		
		return result;
	}
	
	public MassiveDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.userApi = apiMaker.getUserApiMaker();
		this.repoApi = apiMaker.getRepoApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}

}
