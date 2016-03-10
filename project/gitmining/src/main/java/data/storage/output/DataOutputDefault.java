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
import common.model.GitUserBeans;
import common.model.ObjChannelWithBlockingQueue;
import common.model.RepositoryBeans;
import common.model.filter.PureDataTransFilter;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import data.storage.directory.DirectoryMakerDefault;
import data.storage.directory.service.DirectoryMaker;
import data.storage.service.DataStorageOutput;

public class DataOutputDefault implements DataStorageOutput {

	private DirectoryMaker dir = DirectoryMakerDefault.getInstance();
	private Gson gson = new Gson();
	
	private static final int OUTPUT_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
	
	@Override
	public ObjChannel<RepositoryMin> getRepoMin() {
		// TODO Auto-generated method stub
		File root = new File(dir.repositoryRoot());
		File[] rootSubs = root.listFiles();
		File[][] splitSubs = splitFileArray(rootSubs, OUTPUT_THREAD_NUM);
		
		//Create a channel transferring file directories.
		ObjChannel<File> directoryChan = new ObjChannelWithBlockingQueue<>();
		PureDataTransFilter[] directoryTransfer = new PureDataTransFilter[OUTPUT_THREAD_NUM];
		MultiSourceSwitch<File> directorySwitch = new BasicSourceSwitch<>(directoryChan);
		for(int i=0;i<OUTPUT_THREAD_NUM;i++) {
			directoryTransfer[i] = new PureDataTransFilter<File>(Arrays.asList(splitSubs[i]), directorySwitch);
		}
		execute(directoryTransfer);
		
		
		//TODO 待完成任务流
		
		return null;
	}

	@Override
	public Repository getRepository(RepositoryMin minInfo)
			throws TargetNotFoundException, DataCorruptedException {
		return getRepository(minInfo.getFull_name());
	}

	@Override
	public Repository getRepository(String fullName)
			throws TargetNotFoundException, DataCorruptedException {
		File directory = new File(dir.repositoryDirectory(fullName));
		File content = new File(directory, dir.repositoryName(fullName));
		
		checkExistence(content);
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getUser(GitUserMin minInfo) throws TargetNotFoundException,
			DataCorruptedException {
		return getUser(minInfo.getLogin());
	}

	@Override
	public GitUser getUser(String login) throws TargetNotFoundException,
			DataCorruptedException {
		
		File directory = new File(dir.userDirectory(login));
		File content = new File(directory,dir.userName(login));
		
		checkExistence(content);
		
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
		int eachNum = totalNum/pieces,lastPieceNum = totalNum-eachNum*(pieces-1);
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
}
