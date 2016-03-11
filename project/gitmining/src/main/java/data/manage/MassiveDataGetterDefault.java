package data.manage;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.MassiveDataGetter;
import data.storage.service.DataStorageOutput;
import data.storage.service.StorageServiceFactory;

/**
 * 单例对象。
 * @author xjh14
 *
 */
public class MassiveDataGetterDefault extends MassiveDataGetter {
	
	private static MassiveDataGetter instance = new MassiveDataGetterDefault();
	
	private DataStorageOutput storage = StorageServiceFactory.getInstance().getOutput();
	
	
	private MassiveDataGetterDefault() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public int getRepoNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjChannel<GitUserMin> getUserMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public static MassiveDataGetter getInstance() {
		return instance;
	}
	
	
}
