package data.storage.service;

import java.util.Collection;

import common.service.GitUser;
import common.service.Repository;
import common.util.ObjChannel;



public interface DataStorageInput {

	public void saveRepository(Repository repo);
	
	public void saveRepository(Collection<Repository> repos);
	
	public void saveRepository(ObjChannel<Repository> repoChan);
	
	public void saveUser(GitUser user);
	
	public void saveUser(Collection<GitUser> users);
	
	public void saveUser(ObjChannel<GitUser> userChan);
	
}
