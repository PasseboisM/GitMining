package data.storage.directory.service;

import common.service.GitUserMin;
import common.service.RepositoryMin;

public interface DirectoryMaker {

	public String repositoryDirectory(RepositoryMin minInfo);
	
	public String repositoryName(RepositoryMin minInfo);
	
	public String userDirectory(GitUserMin minInfo);
	
	public String userName(GitUserMin minInfo);
}
