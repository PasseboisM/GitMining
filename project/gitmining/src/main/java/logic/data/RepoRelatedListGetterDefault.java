package logic.data;

import java.util.ArrayList;
import java.util.List;

import common.service.Repository;
import logic.service.RepoRelatedListGetter;

public class RepoRelatedListGetterDefault implements RepoRelatedListGetter {

	
//	private RepoListDataGetter getter = DataServiceFactory.getInstance().getRepoListDataGetter();

	/*@Override
	public List<GitUser> getContributorNames(String fullName) throws IOException {
		return getter.getContributors(fullName);
	}

	@Override
	public List<GitUser> getCollaboratorNames(String fullName) throws IOException {
		return getter.getCollaborators(fullName);
	}*/


	@Override
	public List<Repository> getRelatedRepoNames(String fullName) {
		return new ArrayList<>();
	}
}
