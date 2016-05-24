package data.manage;

import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import data.storage.service.DataStorageOutput;
import data.storage.service.StorageServiceFactory;

/**
 * 
 * @author River
 * @deprecated 迭代三已经不再提供本地数据支持
 */
public class SpecificDataGetterDefault extends SpecificDataGetter {

	private static SpecificDataGetter instance = new SpecificDataGetterDefault();
	
	private DataStorageOutput fromStorage = StorageServiceFactory.getInstance().getOutput();
	private SpecificDataSource fromNetwork = NetworkServiceFactory.getInstance().getSpecificDataSource();
	
	private SpecificDataGetterDefault() {}
	
	@Override
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException{
		
		Repository result = null;
		
		try {
			result = DataBuffer.searchRepository(source.getFull_name());
		} catch (TargetNotFoundException e) {
			
			if(DataServiceFactory.isUsingNetwork()) {
				result = fromNetwork.getSpecificRepo(source.getFull_name());
			} else {
				try {
					result = fromStorage.getRepository(source.getFull_name());
				} catch (Exception e1) {
					e1.printStackTrace();
					System.out.println("Error getting:"+source.getFull_name());
					throw new DataCorruptedException();
				}
			}
			
			DataBuffer.addRepository(result);
		}
		
		return result;
	}

	@Override
	public GitUser getSpecificGitUser(GitUserMin source) throws NetworkException, DataCorruptedException {
		
		GitUser result = null;
		
		try {
			result = DataBuffer.searchUser(source.getLogin());
		} catch (TargetNotFoundException e) {
			
			if(DataServiceFactory.isUsingNetwork()) {
				result = fromNetwork.getSpecificUser(source.getLogin());
			} else {
				try {
					result = fromStorage.getUser(source.getLogin());
				} catch (Exception e1) {
					throw new DataCorruptedException();
				}
			}
			
			DataBuffer.addUser(result);
			
		}
		return result;
	}
	
	public static SpecificDataGetter getInstance() {
		return instance;
	}
	

}
