package network.service;

import java.util.List;

import common.param_obj.RepositorySearchParam;
import common.service.Repository;

public interface GHDataSource {
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam);
}
