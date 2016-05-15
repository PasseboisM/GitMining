package logic.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import common.service.Repository;

public interface RepoRelatedListGetter {
	public List<Repository> getRelatedRepoNames(String fullName);
	public List<GitUser> getContributorNames(String fullName) throws IOException;
	public List<GitUser> getCollaboratorNames(String fullName) throws IOException;
}
