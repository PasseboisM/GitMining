package network.service.stub;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import network.service.MassiveDataSource;

public class MassiveDataSource_stub implements MassiveDataSource{

	public ObjChannel<String> getRepoNames() {
		return null;
	}

	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		return null;
	}

	public ObjChannel<Repository> getRepoInfo() {
		return null;
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		return null;
	}

	public ObjChannel<GitUser> getUserInfo() {
		return null;
	}
	
}
