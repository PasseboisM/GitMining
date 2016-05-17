package data.manage;

import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
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
	public int getUserNumber() {
		return fromStorage.getUserNumber();
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


	@Override
	public List<Repository> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getNumOfRepositories() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<GitUser> getUsers(int page, int numPerPage, UserSortSandard sortStandard)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Repository> searchRepository(RepositorySearchParam params)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<GitUser> searchUser(UserSearchParam params) throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
