package network.api.stub;

import common.service.RepositoryMin;
import network.api.service.RepoApiMaker;

public class RepoApiMaker_stub implements RepoApiMaker {

	public String makeRepoInfoApi(RepositoryMin source) {
		return "https://api.github.com/repos/"+source.getFull_name();
	}

	public String makeRepoNamesApi() {
		return "http://www.gitmining.net/api/repository/names";
	}

	public String makeRepoInfoApi(String fullName) {
		return "https://api.github.com/repos/"+fullName;
	}

	@Override
	public String makeRepoContributorLoginsApi(String repoFullName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String makeBranchesApi(RepositoryMin source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String makeBranchesApi(String fullName) {
		// TODO Auto-generated method stub
		return null;
	}

}
