package data.service.stub;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.MassiveDataGetter;
import network.service.MassiveDataSource;

public class MassiveDatGetter_stub implements MassiveDataGetter{

	public ObjChannel<RepositoryMin> getRepoMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjChannel<GitUserMin> getUserMinInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}
