package data.manage;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import data.service.RepoListDataGetter;
import network.service.NetworkServiceFactory;
import network.service.RepoRelatedDataSource;

public class RepoListDataGetterDefault extends RepoListDataGetter {
	
	private static RepoListDataGetter instance = new RepoListDataGetterDefault();
	
	private RepoRelatedDataSource fromNetwork = NetworkServiceFactory.getInstance().getRepoRelatedDataSource();
			
	private RepoListDataGetterDefault() {}
	
	public static RepoListDataGetter getInstance() {
		return instance;
	}
	@Override
	public List<GitUser> getContributors(String fullName) throws IOException {
		return fromNetwork.listContributors(fullName);
	}

	@Override
	public List<GitUser> getCollaborators(String fullName) throws IOException {
		return fromNetwork.listCollaborators(fullName);
	}

}
