package network.service;

import java.util.List;

import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;

public interface GHDataSource {
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam) throws NetworkException;
	public List<GitUser> searchUser(UserSearchParam userSearchParam) throws NetworkException;
	public boolean isCredentialValid(String login,String password) throws NetworkException;
}
