package logic.data;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.service.GitUser;
import common.service.Repository;

import logic.service.GeneralGetter;

public class GeneralGetterDefault implements GeneralGetter {

	@Override
	public List<Repository> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfRepositories() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GitUser> getUsers(int page, int numPerPage,
			UserSortSandard sortStandard) {
		// TODO Auto-generated method stub
		return null;
	}

}
