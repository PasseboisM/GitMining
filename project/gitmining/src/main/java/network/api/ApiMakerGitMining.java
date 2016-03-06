package network.api;

import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;

public class ApiMakerGitMining extends ApiMakerService {

	private RepoApiMaker repo = new RepoApiMakerGitMining();
	private UserApiMaker user = new UserApiMakerGitMining();
	
	@Override
	public RepoApiMaker getRepoApiMaker() {
		return repo;
	}

	@Override
	public UserApiMaker getUserApiMaker() {
		return user;
	}

}
