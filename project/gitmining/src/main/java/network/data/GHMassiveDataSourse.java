package network.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.NetworkException;
import common.model.BasicSourceSwitch;
import common.model.ObjChannelWithBlockingQueue;
import common.model.filter.GeneralProcessFilter;
import common.model.filter.PureDataTransFilter;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GHMassiveDataSourse {
	private Gson gson = new Gson(); 
	
	private static final int SUGGESTED_THREAD_NUM = Runtime.getRuntime().availableProcessors() *2;
	
	private void execute(Runnable[] runnables) {
		Executor exec = Executors.newCachedThreadPool();
		for(Runnable r: runnables) {
			exec.execute(r);
		}
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
	public ObjChannel<String> getRepoNames() throws NetworkException, IOException {
		//新建管道、集流器，获取URL
		ObjChannel<String> channel = new ObjChannelWithBlockingQueue<String>();
		MultiSourceSwitch<String> sourceSwitch = new BasicSourceSwitch<String>(channel);

		BufferedReader reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"), "names.json")));
		String json = reader.readLine();
		reader.close();
		
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
	
	
	public ObjChannel<GHRepository> getRepo() throws NetworkException, IOException {
		
		//初始化传输Github项目名称的管道
		ObjChannel<String> namesChannel = this.getRepoNames();
		
		//初始化项目内容对象（Java Object）的传输管道及由JSON向JavaObject进行转化的过滤器数组
		ObjChannel<GHRepository> objChannel = new ObjChannelWithBlockingQueue<GHRepository>();
		GHNameFilter[] RPOSources = new GHNameFilter[SUGGESTED_THREAD_NUM];
		MultiSourceSwitch switchJSONToObj = new BasicSourceSwitch(objChannel);
		for(int i=0;i<SUGGESTED_THREAD_NUM;i++) {
			RPOSources[i] = new GHNameFilter(namesChannel,  switchJSONToObj);
		}
		//执行由JSON向JavaObject进行转化的过滤器线程
		execute(RPOSources);
		
		return objChannel;
	}
	
	class GHNameFilter extends GeneralProcessFilter<String, GHRepository> {
		//每次转换的数量
		private static final int page = 20;
		
		MultiSourceSwitch<GHRepository> sourceSwitch = null;
		
		public GHNameFilter(ObjChannel<String> input,
				ObjChannel<GHRepository> output) {
			super(input,output,page);
		}
		
		public GHNameFilter(ObjChannel<String> input,
				MultiSourceSwitch<GHRepository> output) {
			super(input,output,page);
			this.sourceSwitch = output;
			output.register(this);
		}


		@Override
		public List<GHRepository> process(List<String> get) {
			List<GHRepository> result = new ArrayList<GHRepository>(page);
			for(String name: get) {
				try {
					GitHub github = GHNetworkServiceFactory.getGitHub();
					GHRepository repository = github.getRepository(name);
					if(repository!=null)
						result.add(repository);
					else
						System.out.println("get null repo for name"+name);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
	}
}
