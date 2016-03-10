package data.storage.service;

import common.exception.DataCorruptedException;
import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;

public interface DataStorageOutput {

	public ObjChannel<RepositoryMin> getRepoMin();
	
	public Repository getRepository(RepositoryMin minInfo) throws TargetNotFoundException, DataCorruptedException;
	
	public Repository getRepository(String fullName) throws TargetNotFoundException, DataCorruptedException;
	
	public ObjChannel<GitUserMin> getUserMin();
	
	public GitUser getUser(GitUserMin minInfo) throws TargetNotFoundException, DataCorruptedException;
	
	public GitUser getUser(String login) throws TargetNotFoundException, DataCorruptedException;
	
}
