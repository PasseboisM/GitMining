package data.db.service;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.param_obj.RepositorySearchParam;

/**
 * TODO
 */
public interface DBRepoService {

	public int getNumOfRepo();
	
	public List<String> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) throws IndexOutOfBoundsException;
	
	public List<String> searchRepository(RepositorySearchParam params);
}
