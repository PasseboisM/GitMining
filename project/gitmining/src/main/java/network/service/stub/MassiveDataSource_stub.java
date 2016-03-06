package network.service.stub;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import network.service.MassiveDataSource;

public class MassiveDataSource_stub implements MassiveDataSource{

	public ObjChannel<String> getRepoNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<Repository> getRepoInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<GitUser> getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
