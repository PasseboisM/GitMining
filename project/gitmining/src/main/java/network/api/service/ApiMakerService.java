package network.api.service;

public abstract class ApiMakerService {

	public abstract RepoApiMaker getRepoApiMaker();
	
	public abstract UserApiMaker getUserApiMaker();
	
	public static ApiMakerService getInstance() {
		return null;//TODO
	}
}
