package network.api.stub;

import network.api.service.AnalysisApiMaker;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.SearchApiMaker;
import network.api.service.TestConnectionApiMaker;
import network.api.service.UserApiMaker;

public class ApiMakerService_stub extends ApiMakerService {

	@Override
	public RepoApiMaker getRepoApiMaker() {
		return new RepoApiMaker_stub();
	}

	@Override
	public UserApiMaker getUserApiMaker() {
		return new UserApiMaker_stub();
	}

	@Override
	public TestConnectionApiMaker getTestConnectionApiMaker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchApiMaker getSearchApiMaker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AnalysisApiMaker getAnalysisApiMaker() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
