package network.api;

import network.api.service.AnalysisApiMaker;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.SearchApiMaker;
import network.api.service.TestConnectionApiMaker;
import network.api.service.UserApiMaker;

public class ApiMakerGitMining extends ApiMakerService {

	private RepoApiMaker repo = new RepoApiMakerGitMining();
	private UserApiMaker user = new UserApiMakerGitMining();
	private TestConnectionApiMaker conn = new TestConnectionApiMakerGitMining();
	private SearchApiMaker search = new SearchApiMakerGitMining();
	private AnalysisApiMaker analysis = new AnalysisApiMakerGitMining();
	
	@Override
	public RepoApiMaker getRepoApiMaker() {
		return repo;
	}

	@Override
	public UserApiMaker getUserApiMaker() {
		return user;
	}

	@Override
	public TestConnectionApiMaker getTestConnectionApiMaker() {
		return conn;
	}

	@Override
	public SearchApiMaker getSearchApiMaker() {
		return search;
	}
	
	@Override
	public AnalysisApiMaker getAnalysisApiMaker(){
		return analysis;
	}

}
