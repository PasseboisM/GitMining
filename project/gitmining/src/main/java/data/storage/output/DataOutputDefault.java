package data.storage.output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import common.exception.DataCorruptedException;
import common.exception.TargetNotFoundException;
import common.model.BasicSourceSwitch;
import common.model.ObjChannelWithBlockingQueue;
import common.model.beans.BeansTranslator;
import common.model.beans.GitUserBeans;
import common.model.beans.RepositoryBeans;
import common.model.filter.GeneralProcessFilter;
import common.model.filter.PureDataTransFilter;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.Checkable;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import data.storage.directory.DirectoryMakerDefault;
import data.storage.directory.service.DirectoryMaker;
import data.storage.service.DataStorageOutput;
@SuppressWarnings({"rawtypes","unchecked"})
public class DataOutputDefault implements DataStorageOutput {

	private DirectoryMaker dir = DirectoryMakerDefault.getInstance();
	private Gson gson = new Gson();
	
	private static final int OUTPUT_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
	
	@Override
	public ObjChannel<RepositoryMin> getRepoMin() {
		//获取数据中的项目根目录
		File root = new File(dir.repositoryRoot());
		System.out.println(root);
		//获取项目文件夹下的子文件夹数组（子文件夹的名字即为项目持有者的名字）
		File[] rootSubs = root.listFiles();
		//将子文件夹数组分解为多个部分，便于输出
		File[][] splitSubs = splitFileArray(rootSubs, OUTPUT_THREAD_NUM);
		
		//初始化文件传输管道、文件转换过滤器、集流器
		ObjChannel<File> directoryChan = new ObjChannelWithBlockingQueue<>();
		PureDataTransFilter[] directoryTransfer = new PureDataTransFilter[OUTPUT_THREAD_NUM];
		MultiSourceSwitch<File> directorySwitch = new BasicSourceSwitch<>(directoryChan);
		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
			directoryTransfer[i] = new PureDataTransFilter<File>(Arrays.asList(splitSubs[i]), directorySwitch);
		}
		//执行转换过程（将文件列表转换至管道中以便传输）
		execute(directoryTransfer);
		
		
		//初始化项目信息最小子集传输管道、格式转换过滤器、集流器
		ObjChannel<RepositoryMin> minInfoChan = new ObjChannelWithBlockingQueue<>();
		JSONFileSearchReadDeserializeFilter[] deserializers = new JSONFileSearchReadDeserializeFilter[OUTPUT_THREAD_NUM];
		MultiSourceSwitch<RepositoryMin> minInfoSwitch = new BasicSourceSwitch<>(minInfoChan);
		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
			deserializers[i] = new JSONFileSearchReadDeserializeFilter<RepositoryMin>
				(directoryChan, minInfoSwitch, 20, RepositoryMin.class);
		}
		//执行将文件到项目信息最小子集的转换
		execute(deserializers);
		
		return minInfoChan;
	}

	@Override
	public Repository getRepository(RepositoryMin minInfo)
			throws TargetNotFoundException, DataCorruptedException {
		return getRepository(minInfo.getFull_name());
	}

	@Override
	public Repository getRepository(String fullName)
			throws TargetNotFoundException, DataCorruptedException {
		//获取项目对应的持有者文件夹
		File directory = new File(dir.repositoryDirectory(fullName));
		//获取项目对应的具体文件
		File content = new File(directory, dir.repositoryName(fullName));
		//检查文件是否存在
		checkExistence(content);
		//获取文件中的JSON字符串
		String json = readLine(content);
		
		try {
			Repository result = gson.fromJson(json, RepositoryBeans.class);
			return result;			
		} catch (Exception e) {
			throw new DataCorruptedException();
		}
	}

	@Override
	public ObjChannel<GitUserMin> getUserMin() {
		//获取数据中的用户根目录
		File directory = new File(dir.userRoot());
		//获取用户文件夹下的文件数组
		File[] contents = directory.listFiles();
		//将文件数组分割为多个子部分
		File[][] splitContents = splitFileArray(contents, OUTPUT_THREAD_NUM);
		
		
		//初始化将文件列表转化进入的通道和转化所需的过滤器、集流器
		ObjChannel<File> fileChan = new ObjChannelWithBlockingQueue<>();
		PureDataTransFilter[] transfers = new PureDataTransFilter[OUTPUT_THREAD_NUM];
		MultiSourceSwitch<File> fileSwitch = new BasicSourceSwitch<>(fileChan);
		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
			transfers[i] = new PureDataTransFilter<File>(Arrays.asList(splitContents[i]), fileSwitch);
		}
		//执行转化过程，将文件数组转化进通道中
		execute(transfers);
		
		
		//初始化将通道中文件（保存JSON字符串）向GitUser进行转化的通过，转化用到的过滤器、集流器
		ObjChannel<GitUserMin> minInfoChan = new ObjChannelWithBlockingQueue<>();
		JSONFileReadDeserializeFilter[] deserializers = new JSONFileReadDeserializeFilter[OUTPUT_THREAD_NUM];
		MultiSourceSwitch<GitUserMin> minInfoSwitch = new BasicSourceSwitch<>(minInfoChan);
		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
			deserializers[i] = new JSONFileReadDeserializeFilter<GitUserMin>
				(fileChan, minInfoSwitch, 20, GitUserMin.class);
		}
		//执行转化过程
		execute(deserializers);
		
		return minInfoChan;
		
	}

	@Override
	public GitUser getUser(GitUserMin minInfo) throws TargetNotFoundException,
			DataCorruptedException {
		return getUser(minInfo.getLogin());
	}

	@Override
	public GitUser getUser(String login) throws TargetNotFoundException,
			DataCorruptedException {
		//获得GitUser的登录名（login）对应的文件
		File directory = new File(dir.userDirectory(login));
		File content = new File(directory,dir.userName(login));
		//检查文件是否存在
		checkExistence(content);
		//获取文件中的JSON字符串
		String json = readLine(content);
		
		try {
			GitUser result = gson.fromJson(json, GitUserBeans.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataCorruptedException();
		}
		
	}
	
	private void execute(Runnable[] runnables) {
		Executor exec = Executors.newCachedThreadPool();
		for(Runnable r: runnables) {
			exec.execute(r);
		}
	}
	
//	public static void main(String[] args) {
//		File root = new File(System.getProperty("user.dir")+"\\LocalData\\repository\\");
//		File[] fs = root.listFiles();
//		try {
//			for (int i=0;i<50;i++) {
//				System.out.println(fs[i].getAbsolutePath());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	private File[][] splitFileArray(File[] directory,int pieces) {
		assert pieces > 0;
		int totalNum = directory.length;
		int eachNum = totalNum/pieces;
//		int lastPieceNum = totalNum-eachNum*(pieces-1);
		File[][] result = new File[pieces][];
		for(int i=0;i<pieces;i++) {
			
			if(i==pieces-1) {	//last piece
				result[i] = subFileArray(i*eachNum, totalNum, directory);
			} else {			//not the last piece
				result[i] = subFileArray(i*eachNum, (i+1)*eachNum, directory);
			}
			
		}
		return result;
	}
	
	private File[] subFileArray(int start,int end,File[] original) {
		assert start<=end;
		File[] result = new File[end-start];
		for(int i=start;i<end;i++) {
			result[i-start] = original[i];
		}
		return result;
	}
	
 	private void checkExistence(File f) throws TargetNotFoundException {
		try {
			if(f.exists()) {
				return;
			} else {
				throw new TargetNotFoundException();
			}
		} catch (Exception e) {
			throw new TargetNotFoundException();
		}
	}

	private String readLine(File contentFile) throws TargetNotFoundException {
		try {
			FileReader fw = new FileReader(contentFile);
			BufferedReader br = new BufferedReader(fw);
			String result = br.readLine();
			br.close();
			fw.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw new TargetNotFoundException();
		}
	}
	
	/**
	 * Goes one layer directory deeper to search for JSON files and transfer.
	 * 译：用于读取并将保存json的文件反序列化的过滤器
	 * @author xjh14
	 *
	 * @param <T>
	 */
	class JSONFileSearchReadDeserializeFilter<T> extends GeneralProcessFilter<File, T> {

		Class<T> objectiveType = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		public JSONFileSearchReadDeserializeFilter(ObjChannel<File> directories,
				MultiSourceSwitch<T> output, int page, Class<T> objective) {
			super(directories, output, page);
			this.objectiveType = objective;
		}

		@Override
		public List<T> process(List<File> get) {
			List<T> result = new ArrayList<>(page);
			//便利项目持有者文件夹集合的片段中的每个文件夹
			for (File dir : get) {
				//得到项目持有者文件夹中的每个项目文件
				File[] files = dir.listFiles();
				for (File content : files) {
					try {
						fr = new FileReader(content);
						br = new BufferedReader(fr);
						String s = br.readLine();

						T partial = (T) gson.fromJson(s, BeansTranslator.getBeans(objectiveType));

						// TODO 尽快将系统中大范围修改为Checkable，避免强制转型
						Checkable check = (Checkable) partial;
						if (!check.checkValidity()) {
							continue;
						} else {
							result.add(partial);
						}

					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							br.close();
							fr.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return result;
		}
	}
	
	/**
	 * Read the provided JSON files and convert to objective object.
	 * 译：用于将文件反序列化转化成对象的过滤器 
	 * @author xjh14
	 *
	 * @param <T>
	 */
	class JSONFileReadDeserializeFilter<T> extends GeneralProcessFilter<File, T> {

		Class<T> objectiveType = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		public JSONFileReadDeserializeFilter(ObjChannel<File> contents,
				MultiSourceSwitch<T> output, int page, Class<T> objective) {
			super(contents, output, page);
			this.objectiveType = objective;
		}

		@Override
		public List<T> process(List<File> get) {
			List<T> result = new ArrayList<>(page);
			//遍历文件列表片段的每一个文件
			for(File content:get) {
				try {
					fr = new FileReader(content);
					br = new BufferedReader(fr);
					String s = br.readLine();
						
					T partial = (T) gson.fromJson(s, BeansTranslator.getBeans(objectiveType));
					
					//TODO 尽快将系统中大范围修改为Checkable，避免强制转型
					Checkable check = (Checkable)partial;
					if(!check.checkValidity()) {
						continue;
					} else {
						result.add(partial);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return result;
		}
	}
	
//	/**
//	 * Wipe out the data with noise. Only used while developing.
//	 */
//	private void cleanUserData() {
//		File directory = new File(dir.userRoot());
//		File[] contents = directory.listFiles();
//		File[][] splitContents = splitFileArray(contents, OUTPUT_THREAD_NUM);
//		
//		ObjChannel<File> fileChan = new ObjChannelWithBlockingQueue<>();
//		PureDataTransFilter[] transfers = new PureDataTransFilter[OUTPUT_THREAD_NUM];
//		MultiSourceSwitch<File> fileSwitch = new BasicSourceSwitch<>(fileChan);
//		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
//			transfers[i] = new PureDataTransFilter<File>(Arrays.asList(splitContents[i]), fileSwitch);
//		}
//		execute(transfers);
//		
//
//		ObjChannel<GitUser> minInfoChan = new ObjChannelWithBlockingQueue<>();
//		JSONFileCleanerFilter[] deserializers = new JSONFileCleanerFilter[OUTPUT_THREAD_NUM];
//		MultiSourceSwitch<GitUser> minInfoSwitch = new BasicSourceSwitch<>(minInfoChan);
//		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
//			deserializers[i] = new JSONFileCleanerFilter<GitUser>
//				(fileChan, minInfoSwitch, 20, GitUser.class);
//		}
//		execute(deserializers);
//		
//	}
//	
//	private static volatile int totalCleaned = 0;
//	
//	/**
//	 * Read the provided JSON files and convert to objective object.
//	 * @author xjh14
//	 *
//	 * @param <T>
//	 */
//	class JSONFileCleanerFilter<T> extends GeneralProcessFilter<File, T> {
//
//		
//		
//		Class<T> objectiveType = null;
//		FileReader fr = null;
//		BufferedReader br = null;
//		
//		public JSONFileCleanerFilter(ObjChannel<File> contents,
//				MultiSourceSwitch<T> output, int page, Class<T> objective) {
//			super(contents, output, page);
//			this.objectiveType = objective;
//		}
//
//		@Override
//		public List<T> process(List<File> get) {
//			List<T> result = new ArrayList<>(page);
//			for(File content:get) {
//				try {
//					fr = new FileReader(content);
//					br = new BufferedReader(fr);
//					String s = br.readLine();
//						
//					T partial = (T) gson.fromJson(s, getBeans(objectiveType));
//					Checkable check = (Checkable) partial;
//					if(check==null) System.out.println(content.getAbsolutePath());
//					if(!check.checkValidity()) {
//						try {
//							br.close();
//							fr.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//						totalCleaned ++ ;
//						System.out.println("Deleting "+totalCleaned);
//						System.out.println(partial);
//						content.delete();
//					} else {
//						result.add(partial);
//					}
//					
//						
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					
//				}
//			}
//			return result;
//		}
//	}
//	
	

	@Override
	public int getRepoNumber() {
		//TODO 真正实现
		return 3086;
	}
//	
//	public void cleanRepo() {
//		File root = new File(dir.repositoryRoot());
//		System.out.println(root);
//		File[] rootSubs = root.listFiles();
//		File[][] splitSubs = splitFileArray(rootSubs, OUTPUT_THREAD_NUM);
//		
//		//Create a channel transferring file directories.
//		ObjChannel<File> directoryChan = new ObjChannelWithBlockingQueue<>();
//		PureDataTransFilter[] directoryTransfer = new PureDataTransFilter[OUTPUT_THREAD_NUM];
//		MultiSourceSwitch<File> directorySwitch = new BasicSourceSwitch<>(directoryChan);
//		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
//			directoryTransfer[i] = new PureDataTransFilter<File>(Arrays.asList(splitSubs[i]), directorySwitch);
//		}
//		execute(directoryTransfer);
//		
//		ObjChannel<Repository> minInfoChan = new ObjChannelWithBlockingQueue<>();
//		JSONFileCleanFilter[] deserializers = new JSONFileCleanFilter[OUTPUT_THREAD_NUM];
//		MultiSourceSwitch<Repository> minInfoSwitch = new BasicSourceSwitch<>(minInfoChan);
//		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
//			deserializers[i] = new JSONFileCleanFilter<Repository>
//				(directoryChan, minInfoSwitch, 20, Repository.class);
//		}
//		execute(deserializers);
//		
//	}
//	
//	private static volatile int totalCleaned = 0;
//	
//	class JSONFileCleanFilter<T> extends GeneralProcessFilter<File, T> {
//
//		Class<T> objectiveType = null;
//		FileReader fr = null;
//		BufferedReader br = null;
//		
//		public JSONFileCleanFilter(ObjChannel<File> directories,
//				MultiSourceSwitch<T> output, int page, Class<T> objective) {
//			super(directories, output, page);
//			this.objectiveType = objective;
//		}
//
//		@Override
//		public List<T> process(List<File> get) {
//			List<T> result = new ArrayList<>(page);
//			for(File dir:get) {
//				File[] files = dir.listFiles();
//				for(File content:files) {
//					try {
//						fr = new FileReader(content);
//						br = new BufferedReader(fr);
//						String s = br.readLine();
//						
//						T partial = (T) gson.fromJson(s, getBeans(objectiveType));
//						Checkable check = (Checkable) partial;
//						
//						if((check==null)||(!check.checkValidity())) {
//							try {
//								br.close();
//								fr.close();
//							} catch (IOException e) {
//								e.printStackTrace();
//							}
//							totalCleaned ++;
//							System.out.println("Deleting "+totalCleaned);
//							System.out.println(partial);
//							content.delete();
//						} else {
//							result.add(partial);
//						}
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			return result;
//		}
//	}
//	
//	
//	
//	
//	public static void main(String[] args) {
//		DataOutputDefault data = new DataOutputDefault();
//		data.cleanRepo();
//	}
}
