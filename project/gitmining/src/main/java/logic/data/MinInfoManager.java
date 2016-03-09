package logic.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import common.exception.DataTransferException;
import common.message.LoadProgress;
import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;

/**
 * 在逻辑层保存原始信息索引，并对外提供副本的类。
 * 会在系统启动时开始初始化。
 * @author xjh14
 *
 */
public class MinInfoManager {

	private static MinInfoManager instance = new MinInfoManager();
	
	private AtomicInteger remainingInitMission = new AtomicInteger(1);
	private boolean initException = false;
	
	private volatile List<RepositoryMin> repoMinInfo = new ArrayList<RepositoryMin>(4000);
	private volatile Set<GitUserMin> userMinInfo = new HashSet<GitUserMin>(10000);
	
	public List<RepositoryMin> getRepoMin() {
		return new ArrayList<>(repoMinInfo);
	}
	
	public int getRepoNum() {
		return repoMinInfo.size();
	}
	
	public List<GitUserMin> getUserMin() {
		return new ArrayList<>(userMinInfo);
	}
	
	public int getUserNum() {
		return userMinInfo.size();
	}
	
	public int getTotalRepoNum() {
		return getter.getRepoNumber();
	}
	
	private MassiveDataGetter getter;
	
	private MinInfoManager(){
		getter = DataServiceFactory.getInstance().getMassiveDataGetter();
		ObjChannel<RepositoryMin> repoChan = getter.getRepoMinInfo();
		ObjChannel<GitUserMin> userChan = getter.getUserMinInfo();
		Thread repoInitThread = new Thread(new CollectionWriter<RepositoryMin>(repoChan, repoMinInfo));
		Thread userInitThread = new Thread(new CollectionWriter<>(userChan, userMinInfo));
		repoInitThread.start();
		userInitThread.start();
	}
	
	public static MinInfoManager getInstance() {
		return instance;
	}
	
	public LoadProgress getProgress() {
		return new LoadProgress(getTotalRepoNum(), getRepoNum());
	}
	
	class CollectionWriter<T> implements Runnable {
		private static final int page = 50;
		ObjChannel<T> source = null;
		Collection<T> target = null;
		public CollectionWriter(ObjChannel<T> source, Collection<T> target) {
			this.source = source;
			this.target = target;
		}
		
		@Override
		public void run() {
			try {
				while(source.hasMore()) {
					target.addAll(source.getObj(page));
				}
			} catch (DataTransferException e) {
				setInitException(true);
				e.printStackTrace();
			} finally {
				remainingInitMission.decrementAndGet();
			}
		}
	}

	public boolean isInitException() {
		return initException;
	}

	public void setInitException(boolean initException) {
		this.initException = initException;
	}
	
}
