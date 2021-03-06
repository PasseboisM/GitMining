package network.data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.NetworkException;
import common.model.BasicSourceSwitch;
import common.model.ObjChannelWithBlockingQueue;
import common.model.beans.BeansTranslator;
import common.model.filter.GeneralProcessFilter;
import common.model.filter.JSONStringRPOFilter;
import common.model.filter.PureDataTransFilter;
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
import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

/**
 * 包含了JSON字符串解析功能的数据源，为上层提供能够获取批量信息的ObjChannel
 * 为了提高运行效率，在JSON解析、信息传输的行为上都采用了多线程操作
 * @author xjh14,wza14
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class MassiveDataSourceDefault implements MassiveDataSource {

	
	private Gson gson = new Gson(); 
	private UserApiMaker userApi = null;
	private RepoApiMaker repoApi = null;
	private HTTPConnectionService conn = null;
	private SpecificDataSource spec = NetworkServiceFactory.getInstance().getSpecificDataSource();
	
	private static final int SUGGESTED_THREAD_NUM = Runtime.getRuntime().availableProcessors() *2;
	
	public ObjChannel<String> getRepoNames() throws NetworkException {
		//新建管道、集流器，获取URL
		ObjChannel<String> channel = new ObjChannelWithBlockingQueue<String>();
		MultiSourceSwitch<String> sourceSwitch = new BasicSourceSwitch<String>(channel);
		String url = repoApi.makeRepoNamesApi();
		
		//获取并处理JSON，转化为Java的List对象
		String json = conn.do_get(url);
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json, listTypeType);
		//分割列表为多个子列表，分别进行传输
		List<String>[] listSplit = splitList(repoLists, SUGGESTED_THREAD_NUM);
		//创建过滤器，与用于传输子列表的管道一一对应，将所有传输子列表的管道连接到集流器中统一传输
		PureDataTransFilter[] sources = new PureDataTransFilter[SUGGESTED_THREAD_NUM];
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			sources[i] = new PureDataTransFilter<String>(listSplit[i], sourceSwitch);
		}
		//所有过滤器线程执行传输任务
		execute(sources);
		
		return channel;
	}
	

	
	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException {
		return getRepo(RepositoryMin.class);
	}
	
	public ObjChannel<Repository> getRepoInfo() throws NetworkException {
		return getRepo(Repository.class);
	}

	public ObjChannel<GitUserMin> getUserMinInfo() throws NetworkException {
		return getUser(GitUserMin.class);
	}

	public ObjChannel<GitUser> getUserInfo() throws NetworkException {
		return getUser(GitUser.class);
	}
	
	private <T> ObjChannel<T> getRepo(Class<T> repoClass) throws NetworkException {
		
		//初始化传输Github项目名称的管道
		ObjChannel<String> namesChannel = this.getRepoNames();
		//初始化传输Github项目内容（JSON格式）的管道
		ObjChannel<String> JSONChannel = new ObjChannelWithBlockingQueue<String>();

		
		//初始化由项目名称向项目管道进行转化的过滤器、初始化集流器
		GeneralProcessFilter[] repoJSONGetter = new GeneralProcessFilter[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchNameToJSON = new BasicSourceSwitch(JSONChannel);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			repoJSONGetter[i] = new RepoJSONGetter(namesChannel,switchNameToJSON,50);
		}
		//执行由项目名称向项目内容进行转化的线程
		execute(repoJSONGetter);
		
		
		//初始化项目内容对象（Java Object）的传输管道及由JSON向JavaObject进行转化的过滤器数组
		ObjChannel<T> objChannel = new ObjChannelWithBlockingQueue<T>();
		JSONStringRPOFilter[] RPOSources = new JSONStringRPOFilter[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchJSONToObj = new BasicSourceSwitch(objChannel);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			RPOSources[i] = new JSONStringRPOFilter(JSONChannel, BeansTranslator.getBeans(repoClass), switchJSONToObj);
		}
		//执行由JSON向JavaObject进行转化的过滤器线程
		execute(RPOSources);
		
		return objChannel;
	}
	
	private <T> ObjChannel<T> getUser(Class<T> userClass) throws NetworkException {
		

		String[] loginURL = userApi.allUserAPIs();
		
		//loginURLChannel里面传输的是可用于获取Login的URL(String)
		ObjChannel<String> loginURLChannel = new ObjChannelWithBlockingQueue<>();
		PureDataTransFilter<String> loginURLTrans = new PureDataTransFilter<>(
				Arrays.asList(loginURL), loginURLChannel);
		Executors.newCachedThreadPool().execute(loginURLTrans);

		//JSONListChan中是JSON格式的List，而List中是User的Login
		ObjChannel<String> JSONListChan = new ObjChannelWithBlockingQueue<String>();
		OnlineJSONGetter[] loginListGetter = new OnlineJSONGetter[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchURLToList= new BasicSourceSwitch(JSONListChan);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			loginListGetter[i] = new OnlineJSONGetter(loginURLChannel,switchURLToList,50);
		}
		execute(loginListGetter);
		
		ObjChannel<T> result = new ObjChannelWithBlockingQueue<>();
		UserGetter[] userGetters = new UserGetter[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch<T> resultSwitch = new BasicSourceSwitch(result);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			userGetters[i] = new UserGetter<>(
					JSONListChan, resultSwitch, 5, BeansTranslator.getBeans(userClass));
		}
		execute(userGetters);
		
		return result;
	}
	
	/**
	 * 将大列表按照分裂个数分裂为多个子列表
	 * @param origin 大列表
	 * @param pieces 要求分裂个数
	 * @return 子列表数组
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


	private void execute(Runnable[] runnables) {
		Executor exec = Executors.newCachedThreadPool();
		for(Runnable r: runnables) {
			exec.execute(r);
		}
	}

	public MassiveDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.userApi = apiMaker.getUserApiMaker();
		this.repoApi = apiMaker.getRepoApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}

	/**
	 * 用于由Repository的全名获得Repository详细数据的JSON表示的处理器
	 * @author xjh14
	 */
	class RepoJSONGetter extends GeneralProcessFilter<String, String> {

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
	 * 用于将项目名称转换成贡献者信息的JSON字符串的过滤器
	 */
	class RepoNameToContributorJSONFilter extends GeneralProcessFilter<String, String> {

		private final Type stringListType = new TypeToken<List<String>>() {}.getType();
		
		public RepoNameToContributorJSONFilter(ObjChannel<String> input,
				MultiSourceSwitch<String> output, int page) {
			super(input, output, page);
		}

		@Override
		public List<String> process(List<String> get) {
			List<String> names = new ArrayList<>();
			List<String> result = new ArrayList<>();
			for(String repoName:get) {
				try {
					String rawNames = conn.do_get(repoApi.makeRepoContributorLoginsApi(repoName));
					names = gson.fromJson(rawNames, stringListType);
					if(names!=null) {
						for(String name:names) {
							String rawUserJSON = conn.do_get(userApi.makeUserAPI(name));
							result.add(rawUserJSON);
						}
					}
				} catch (NetworkException e) {
					e.printStackTrace();
					break;
				}
			}
			return result;
		}
		
	}
	
	/**
	 * 专用Filter，可以把JSONLoginList直接转化为T（T为GitUserBeans/GitUserMinBeans）
	 * @author xjh14
	 * Ver: 1.0
	 * Created at: 2016年4月7日
	 * @param <T>
	 */
	class UserGetter<T> extends GeneralProcessFilter<String, T> {

		private final Type stringListType = new TypeToken<List<String>>() {}.getType();
		private Class<T> target = null;
		
		public UserGetter(ObjChannel<String> input,
				MultiSourceSwitch<T> output, int page,Class<T> target) {
			super(input, output, page);
			this.target = target;
		}

		@Override
		public List<T> process(List<String> get) {
			List<T> result = new ArrayList<>(page);
			for(String jsonList: get) {
				if(jsonList==null) continue;
				List<String> loginList = gson.fromJson(jsonList, stringListType);
				for(String login:loginList) {
					try {
						result.add(target.cast(spec.getSpecificUser(login)));
					} catch (NetworkException e) {
//						System.out.println("试图由Login获取GitUser失败："+login);
					}
				}
				
			}
			return result;
		}
		
	}
	
	/**
	 * 用于从网络API获取字符串内容（一般为JSON格式）的Filter
	 * @author xjh14
	 * Ver: 1.0
	 * Created at: 2016年4月7日
	 */
	class OnlineJSONGetter extends GeneralProcessFilter<String, String> {

		public OnlineJSONGetter(ObjChannel<String> input,
				MultiSourceSwitch<String> output, int page) {
			super(input, output, page);
		}

		@Override
		public List<String> process(List<String> get) {
			List<String> result = new ArrayList<>(page);
			for(String url: get) {
				String temp = null;
				try {
					temp = conn.do_get(url);
					result.add(temp);
				} catch (NetworkException e) {
					System.out.println("获取网络数据失败:"+url);
					continue;
				}
			}
			return result;
		}
		
	}
}
