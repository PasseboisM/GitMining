package data.db.service;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.TargetNotFoundException;
import common.param_obj.RepositorySearchParam;


public interface DBRepoService {

	public int getNumOfRepo();
	
	public List<String> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) throws IndexOutOfBoundsException;
	
	public List<String> searchRepository(RepositorySearchParam params);
	
	public String getRepository(String fullName) throws TargetNotFoundException;
}
