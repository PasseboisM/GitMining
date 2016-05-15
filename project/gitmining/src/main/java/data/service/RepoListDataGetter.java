package data.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import data.manage.RepoListDataGetterDefault;

public abstract class RepoListDataGetter {
	
	public abstract List<GitUser> getContributors(String fullName) throws IOException;
	public abstract List<GitUser> getCollaborators(String fullName) throws IOException;
	public static RepoListDataGetter getInstance() {
		return RepoListDataGetterDefault.getInstance();
	}
}
