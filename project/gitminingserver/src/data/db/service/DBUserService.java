package data.db.service;

import java.util.List;

import common.enumeration.sort_standard.UserSortSandard;
import common.param_obj.UserSearchParam;


public interface DBUserService {

	public int getNumOfUser();
	
	public  List<String> getUsers(int page, int numPerPage,
			UserSortSandard sortStandard) throws IndexOutOfBoundsException;
	
	public List<String> searchUsers (UserSearchParam param);
}
