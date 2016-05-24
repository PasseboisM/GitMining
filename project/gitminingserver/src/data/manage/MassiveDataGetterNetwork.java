package data.manage;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.service.MassiveDataGetter;

/**
 * 本类为迭代三使用的通过与GitMiningServer通信实现功能的MassiveDataGetter<br />
 * 与之前相比增加了不少接口（但都是直接转化为request给GitMiningServer），之前的<br />
 * MassiveDataGetterDefault直接弃用，不再为本地数据版实现新增接口功能。
 * 
 * @author River
 *
 */
public class MassiveDataGetterNetwork extends MassiveDataGetter {

	private static MassiveDataGetterNetwork instance = new MassiveDataGetterNetwork();
	
	@Override
	public int getRepoNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjChannel<GitUserMin> getUserMinInfo() throws NetworkException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Repository> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfRepositories() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUserNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GitUser> getUsers(int page, int numPerPage, UserSortSandard sortStandard)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Repository> searchRepository(RepositorySearchParam params)
			throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam params) throws NetworkException, DataCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static MassiveDataGetterNetwork getInstance() {
		return instance;
	}

}
