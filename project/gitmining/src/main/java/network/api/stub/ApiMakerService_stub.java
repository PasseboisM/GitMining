package network.api.stub;

import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
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
	

}
