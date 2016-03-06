package network.api.service;

import common.service.RepositoryMin;

public interface RepoApiMaker {
	
	public String makeRepoInfoApi(RepositoryMin source);
	
	public String makeRepoInfoApi(String fullName);
	
	public String makeRepoNamesApi();
}
