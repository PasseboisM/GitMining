package network.service;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;

public interface RepoRelatedDataSource {

	public List<GitUser> listContributors(String fullName) throws IOException;
	public List<GitUser> listCollaborators(String fullName) throws IOException;

}