package data.storage.output;

import common.exception.DataCorruptedException;
import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;

import data.storage.service.DataStorageOutput;

public class DataOutputDB implements DataStorageOutput {

	@Override
	public ObjChannel<RepositoryMin> getRepoMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repository getRepository(RepositoryMin minInfo)
			throws TargetNotFoundException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repository getRepository(String fullName)
			throws TargetNotFoundException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjChannel<GitUserMin> getUserMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getUser(GitUserMin minInfo) throws TargetNotFoundException,
			DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GitUser getUser(String login) throws TargetNotFoundException,
			DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRepoNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
