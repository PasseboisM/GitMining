package data.service.stub;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.MassiveDataGetter;
import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;

public class MassiveDataGetter_stub implements MassiveDataGetter{

	private NetworkServiceFactory networkDataService = NetworkServiceFactory.getInstance();
	private MassiveDataSource dataSource = networkDataService.getMassiveDataSource();
	
	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		return dataSource.getRepoMinInfo();
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		return dataSource.getUserMinInfo();
	}

}