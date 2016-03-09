package data.storage.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;

import common.exception.DataTransferException;
import common.model.BasicSourceSwitch;
import common.model.filter.GeneralProcessFilter;
import common.service.GitUser;
import common.service.Repository;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import data.storage.directory.DirectoryMakerDefault;
import data.storage.directory.service.DirectoryMaker;
import data.storage.service.DataStorageInput;

public class DataInputDefault implements DataStorageInput {

	private static final int SUGGESTED_WRITING_THREAD = 4;
	
	private DirectoryMaker dir = DirectoryMakerDefault.getInstance();
	private Gson gson = new Gson();
	
	@Override
	public void saveRepository(Repository repo) {
		File f = new File(dir.repositoryDirectory(repo));
		try {
			f.createNewFile();
			String json = gson.toJson(repo);
			FileWriter fw = new FileWriter(f);
			fw.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveRepository(Collection<Repository> repos) {
		for(Repository repo:repos) {
			saveRepository(repo);
		}
	}

	@Override
	public void saveRepository(ObjChannel<Repository> repoChan) {
//		GeneralProcessFilter[] writers = new GeneralProcessFilter[SUGGESTED_WRITING_THREAD];
//		MultiSourceSwitch<Boolean> useless = new BasicSourceSwitch<Boolean>(new );
//		for(int i=0;i<SUGGESTED_WRITING_THREAD;i++) {
//			writers[i] = new Writer<Repository>(repoChan, useless, 10){
//
//				@Override
//				public void writeObj(Object obj) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//			}
//		}
		
		while(repoChan.hasMore()) {
			try {
				List<Repository> partial = repoChan.getObj(20);
				saveRepository(partial);
			} catch (DataTransferException e) {
				e.printStackTrace();
			}
			
		}

	}

	@Override
	public void saveUser(GitUser user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(Collection<GitUser> users) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(ObjChannel<GitUser> userChan) {
		// TODO Auto-generated method stub

	}
	
	abstract class Writer<I> extends GeneralProcessFilter<I, Boolean> {

		
		public Writer(ObjChannel<I> input, MultiSourceSwitch<Boolean> output,
				int page) {
			super(input, output, page);
		}

		@Override
		public List<Boolean> process(List<I> get) {
			for(Object obj:get) {
				writeObj(obj);
			}
			return new ArrayList<Boolean>();
		}
		
		public abstract void writeObj(Object obj);
		
	}

}
