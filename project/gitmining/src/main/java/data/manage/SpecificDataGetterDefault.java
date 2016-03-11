package data.manage;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

import data.service.SpecificDataGetter;

public class SpecificDataGetterDefault extends SpecificDataGetter {

	private static SpecificDataGetter instance = new SpecificDataGetterDefault();
	
	private SpecificDataGetterDefault() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Repository getSpecificRepo(RepositoryMin source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getSpecificGitUser(GitUserMin source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static SpecificDataGetter getInstance() {
		return instance;
	}
	

}
