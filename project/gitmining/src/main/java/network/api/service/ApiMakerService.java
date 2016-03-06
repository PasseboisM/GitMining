package network.api.service;

import network.api.ApiMakerGitMining;

public abstract class ApiMakerService {

	public abstract RepoApiMaker getRepoApiMaker();
	
	public abstract UserApiMaker getUserApiMaker();
	
	public static ApiMakerService getInstance() {
		return new ApiMakerGitMining();
	}
}
