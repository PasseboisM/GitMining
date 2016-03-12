package data.service.stub;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.SpecificDataGetter;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class SpecificDataGetter_stub extends SpecificDataGetter {

	
	private NetworkServiceFactory networkDataService = NetworkServiceFactory.getInstance();
	private SpecificDataSource specificDataSource = networkDataService.getSpecificDataSource();
	
	public Repository getSpecificRepo(RepositoryMin source) throws DataCorruptedException {
		// TODO stub测试
		try {
			return specificDataSource.getSpecificRepo(source.getFull_name()) ;
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		return null;
	}

	public GitUser getSpecificGitUser(GitUserMin source) {
		
		try {
			return specificDataSource.getSpecificUser(source.getLogin());
		} catch (NetworkException e ) {
			e.printStackTrace();
		}
		
		return null;
	}

}
