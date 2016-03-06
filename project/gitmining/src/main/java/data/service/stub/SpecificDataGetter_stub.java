package data.service.stub;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.SpecificDataGetter;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class SpecificDataGetter_stub implements SpecificDataGetter {

	
	private NetworkServiceFactory networkDataService = NetworkServiceFactory.getInstance();
	private SpecificDataSource specificDataSource = networkDataService.getSpecificDataSource();
	
	public Repository getSpecificRepo(RepositoryMin source) {
		// TODO stub测试
		return specificDataSource.getSpecificRepo(source.getFull_name()) ;
	}

	public GitUser getSpecificGitUser(GitUserMin source) {
		// TODO Auto-generated method stub
		return null;
	}

}
