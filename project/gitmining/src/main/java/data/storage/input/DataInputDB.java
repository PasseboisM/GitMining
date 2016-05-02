package data.storage.input;

import java.util.Collection;

import common.service.GitUser;
import common.service.Repository;
import common.util.ObjChannel;

import data.storage.service.DataStorageInput;

public class DataInputDB implements DataStorageInput {

	@Override
	public void saveRepository(Repository repo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveRepository(Collection<Repository> repos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveRepository(ObjChannel<Repository> repoChan) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(GitUser user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(Collection<GitUser> users) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveUser(ObjChannel<GitUser> userChan) {
		// TODO Auto-generated method stub

	}

}
