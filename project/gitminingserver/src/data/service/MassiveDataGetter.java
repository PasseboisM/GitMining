package data.service;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortStandard;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import data.manage.MassiveDataGetterNetwork;

/**
 * @author xjh14
 */
public abstract class MassiveDataGetter {

	public abstract List<String> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) throws IndexOutOfBoundsException;
	
	public abstract int getNumOfRepositories();
	
	public abstract int getNumOfUsers();
	
	public abstract List<String> getUsers(int page, int numPerPage,UserSortStandard sortStandard) throws IndexOutOfBoundsException;
	
	public abstract List<String> searchRepository(RepositorySearchParam params);
	
	public abstract List<String> searchUser(UserSearchParam params);
	
	public static MassiveDataGetter getInstance() {
		return MassiveDataGetterNetwork.getInstance();
	}
	
}
