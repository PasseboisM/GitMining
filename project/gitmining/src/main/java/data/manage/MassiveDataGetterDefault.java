package data.manage;

import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;
import common.exception.NetworkException;
import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.DataServiceFactory;
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
	
	private DataStorageOutput fromStorage = null;
	private MassiveDataSource fromNetwork = null;
	
	private MassiveDataGetterDefault() {
		fromStorage = StorageServiceFactory.getInstance().getOutput();
		fromNetwork = NetworkServiceFactory.getInstance().getMassiveDataSource();
	}
	
	
	@Override
	public int getRepoNumber() {
		return fromStorage.getRepoNumber();
	}

	@Override
	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException {
		if(DataServiceFactory.isUsingNetwork()) {
			return fromNetwork.getRepoMinInfo();
		} else {
			return fromStorage.getRepoMin();
		}
	}

	@Override
	public ObjChannel<GitUserMin> getUserMinInfo() throws NetworkException {
		if(DataServiceFactory.isUsingNetwork()) {
			return fromNetwork.getUserMinInfo();
		} else {
			return fromStorage.getUserMin();
		}
	}

	public static MassiveDataGetter getInstance() {
		return instance;
	}
	
	
}
