package logic.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.service.GitUser;
import data.service.DataServiceFactory;
import data.service.RepoListDataGetter;
import logic.service.RepoRelatedListGetter;

public class RepoRelatedListGetterDefault implements RepoRelatedListGetter {

	
	private RepoListDataGetter getter = DataServiceFactory.getInstance().getRepoListDataGetter();

	@Override
	public List<String> getContributorNames(String fullName) throws IOException {
		return getUserName(getter.getContributors(fullName));
	}

	@Override
	public List<String> getCollaboratorNames(String fullName) throws IOException {
		return getUserName(getter.getCollaborators(fullName));
	}

	private List<String> getUserName(List<GitUser> users) {
		List<String> result = new ArrayList<>();
		users.forEach(user->{
			result.add(user.getLogin());
		});
		return result;
	}
}
