package network.api;

import common.service.RepositoryMin;
import network.api.service.RepoApiMaker;

public class RepoApiMakerGitMining implements RepoApiMaker {

	public String makeRepoInfoApi(RepositoryMin source) {
		return this.makeRepoInfoApi(source.getFull_name());
	}

	public String makeRepoInfoApi(String fullName) {
		//Git系统中full name格式为{owner}/{reponame}
		String[] info = fullName.split("/");
		
		assert info.length==2:"仓库全名格式错误，无法生成访问API";
		return "http://www.gitmining.net/api/repository/"+fullName;
	}

	public String makeRepoNamesApi() {
		return "http://www.gitmining.net/api/repository/names";
	}

	@Override
	public String makeRepoContributorLoginsApi(String repoFullName) {
		return "http://www.gitmining.net/api/repository/"+repoFullName+"/contributors/login";
	}

	@Override
	public String makeBranchesApi(RepositoryMin source) {
		return makeBranchesApi(source.getFull_name());
	}

	@Override
	public String makeBranchesApi(String fullName) {
		
		String[] info = fullName.split("/");
		
		assert info.length==2:"仓库全名格式错误，无法生成访问API";
		return "http://www.gitmining.net/api/repository/"+fullName+"/branches";
	}

}
