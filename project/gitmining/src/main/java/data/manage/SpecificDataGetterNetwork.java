package data.manage;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.SpecificDataGetter;

public class SpecificDataGetterNetwork extends SpecificDataGetter {

	@Override
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getSpecificGitUser(GitUserMin source) throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
