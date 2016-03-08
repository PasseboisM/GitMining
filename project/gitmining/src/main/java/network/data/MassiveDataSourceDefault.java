package network.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.model.BasicSourceSwitch;
import common.model.GitUserBeans;
import common.model.GitUserMinBeans;
import common.model.ObjChannelWithBlockingQueue;
import common.model.RepositoryBeans;
import common.model.RepositoryMinBeans;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;
import network.connection.service.HTTPConnectionService;
import network.data.sources.GeneralProcessSource;
import network.data.sources.JSONStringRPOSource;
import network.data.sources.PureDataTransSource;
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
	
	
	private static final int SUGGESTED_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
//	private static final int SUGGESTED_THREAD_NUM = 1;
	
	
	public ObjChannel<String> getRepoNames() throws NetworkException {
		//新建管道、集流器，获取URL
		ObjChannel<String> channel = new ObjChannelWithBlockingQueue<String>();
		MultiSourceSwitch<String> sourceSwitch = new BasicSourceSwitch<String>(channel);
		String url = repoApi.makeRepoNamesApi();
		
		//获取并处理JSON
		String json = conn.do_get(url);
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json, listTypeType);
		
		List<String>[] listSplit = splitList(repoLists, SUGGESTED_THREAD_NUM);
		
		PureDataTransSource[] sources = new PureDataTransSource[SUGGESTED_THREAD_NUM];
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			sources[i] = new PureDataTransSource<String>(listSplit[i], sourceSwitch);
		}
		execute(sources);
		
		return channel;
	}
	

	
	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException, DataTransferException {
		
		return getRepo(RepositoryMin.class);
	}
	

	public ObjChannel<Repository> getRepoInfo() throws NetworkException, DataTransferException {
				
		return getRepo(Repository.class);
	}

	/**
	 * 似乎系统暂时不需要这个功能
	 */
	public ObjChannel<GitUserMin> getUserMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 似乎系统暂时不需要这个功能
	 */
	public ObjChannel<GitUser> getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * TODO 重构时可能要提到别的类中
	 */
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

	private void execute(Runnable[] runnables) {
		Executor exec = Executors.newCachedThreadPool();
		for(Runnable r: runnables) {
			exec.execute(r);
		}
	}
	
	private <T> ObjChannel<T> getRepo(Class<T> repoClass) throws NetworkException {
		ObjChannel<String> namesChannel = this.getRepoNames();
		ObjChannel<String> JSONChannel = new ObjChannelWithBlockingQueue<String>();

		
		GeneralProcessSource[] repoJSONGetter = new GeneralProcessSource[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchNameToJSON = new BasicSourceSwitch(JSONChannel);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			repoJSONGetter[i] = new RepoJSONGetter(namesChannel,switchNameToJSON,50);
		}
		execute(repoJSONGetter);
		
		
		ObjChannel<T> objChannel = new ObjChannelWithBlockingQueue<T>();
		JSONStringRPOSource[] RPOSources = new JSONStringRPOSource[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchJSONToObj = new BasicSourceSwitch(objChannel);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			RPOSources[i] = new JSONStringRPOSource(JSONChannel, getBeans(repoClass), switchJSONToObj);
		}
		execute(RPOSources);
		
		return objChannel;
	}
	
	/**
	 * 用于由Repository的全名获得Repository详细数据的JSON表示的处理器
	 * @author xjh14
	 */
	class RepoJSONGetter extends GeneralProcessSource<String, String> {

		public RepoJSONGetter(ObjChannel<String> namesChannel,
				MultiSourceSwitch switchNameToJSON, int i) {
			super(namesChannel, switchNameToJSON, i);
		}

		@Override
		public List<String> process(List<String> get) {
			List<String> repoJSONs = new ArrayList<String>();
			for(String name:get) {
				String c = null;
				try {
					c = conn.do_get(repoApi.makeRepoInfoApi(name));
				} catch (NetworkException e) {
					e.printStackTrace();
					closeExceptionally();
				}
				repoJSONs.add(c);
			}
			return repoJSONs;
		}
	}
	
	/**
	 * 获取接口对应的Beans模型
	 * TODO 将职责分散给各个接口
	 * @param imp 接口类型
	 * @return Beans类型
	 */
	private Class getBeans(Class imp) {
		if(imp==Repository.class) {
			return RepositoryBeans.class;
		} else if(imp==RepositoryMin.class) {
			return RepositoryMinBeans.class;
		} else if(imp==GitUserMin.class) {
			return GitUserMinBeans.class;
		} else if (imp==GitUser.class) {
			return GitUserBeans.class;
		} else {
			return null;
		}
	}
	
}
