package data.service.stub;

import common.exception.NetworkException;
import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.MassiveDataGetter;
import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;

public class MassiveDataGetter_stub extends MassiveDataGetter{

	private NetworkServiceFactory networkDataService = NetworkServiceFactory.getInstance();
	private MassiveDataSource dataSource = networkDataService.getMassiveDataSource();
	
	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		try {
			return dataSource.getRepoMinInfo();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		try {
			return dataSource.getUserMinInfo();
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getRepoNumber() {
		
		return 3216;
	}

	@Override
	public int getUserNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
