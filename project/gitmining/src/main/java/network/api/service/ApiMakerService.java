package network.api.service;

import network.api.ApiMakerGitMining;

public abstract class ApiMakerService {

	public abstract RepoApiMaker getRepoApiMaker();
	
	public abstract UserApiMaker getUserApiMaker();
	
	public static ApiMakerService getInstance() {
		return new ApiMakerGitMining();
	}
	
	public static NetworkTester[] getNetworkTesters() {
		NetworkTester[] result = {
				new NetworkTester("http://gitmining.net/api/user/rubinius/item/login", "rubinius"),
				new NetworkTester("http://gitmining.net/api/repository/ai/r18n/item/created_at", "2008-12-24T11:15:15Z")
		};
		return result;
	}
}
