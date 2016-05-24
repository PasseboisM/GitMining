package data.storage.directory.service;

import common.service.GitUserMin;
import common.service.RepositoryMin;

public interface DirectoryMaker {

	public String repositoryRoot();
	
	public String userRoot();
	
	public String repositoryDirectory(RepositoryMin minInfo);
	
	public String repositoryDirectory(String fullName);
	
	public String repositoryName(RepositoryMin minInfo);
	
	public String repositoryName(String fullName);
	
	public String userDirectory(GitUserMin minInfo);
	
	public String userDirectory(String login);
	
	public String userName(GitUserMin minInfo);
	
	public String userName(String login);
	
	
}
