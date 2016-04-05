package logic.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import common.exception.DataTransferException;
import common.exception.NetworkException;
import common.message.LoadProgress;
import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import common.util.Observable;
import common.util.Observer;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;

/**
 * 单例对象。
 * 在逻辑层保存原始信息索引，并对外提供副本的类。
 * 会在系统启动时开始初始化。
 * @author xjh14
 *
 */
public class MinInfoManager implements Observable {

	private static MinInfoManager instance = new MinInfoManager();
	
	private AtomicInteger remainingInitMission = new AtomicInteger(1);
	private volatile boolean initException = false;
	
	private volatile List<RepositoryMin> repoMinInfo = new ArrayList<RepositoryMin>(4000);
	private volatile Set<GitUserMin> userMinInfo = new HashSet<GitUserMin>(30000);
	
	ReentrantLock lock = new ReentrantLock();
	
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
		ObjChannel<RepositoryMin> repoChan = null;
		ObjChannel<GitUserMin> userChan = null;
		try {
			repoChan = getter.getRepoMinInfo();
			userChan = getter.getUserMinInfo();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		Thread repoInitThread = new Thread(new CollectionWriter<RepositoryMin>(repoChan, repoMinInfo));
		Thread userInitThread = new Thread(new CollectionWriter<>(userChan, userMinInfo));
		repoInitThread.start();
		userInitThread.start();
	}
	
	public static MinInfoManager getInstance() {
		return instance;
	}
	
	public LoadProgress getProgress() {
		try {
			lock.lock();
			return new LoadProgress(getTotalRepoNum(), getRepoNum(), getUserNum());
		} finally {
			lock.unlock();
		}
		
	}
	
	class CollectionWriter<T> implements Runnable {
		private static final int page = 100;
		ObjChannel<T> source = null;
		Collection<T> target = null;
		public CollectionWriter(ObjChannel<T> source, Collection<T> target) {
			this.source = source;
			this.target = target;
		}
		
		@Override
		public void run() {
			try {
				while (source.hasMore()) {
					lock.lock();
					target.addAll(source.getObj(page));
					lock.unlock();
//					System.out.println(getProgress());
					notifyObservers();
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

	
	
	private List<Observer> observers = new ArrayList<>();
	
	public void notifyObservers() {
		for(Observer ob:observers) {
			ob.update();
		}
	}

	public void addObserver(Observer observer) {
		synchronized (this) {
			observers.add(observer);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		synchronized (this) {
			observers.remove(observer);
		}
	}
	
}
