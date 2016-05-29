package data.manage;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import data.db.service.DBRepoService;
import data.db.service.DBService;
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
	
	private DBRepoService repoDB = DBService.getInstance().getRepoService();
	
	@Override
	public List<String> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfRepositories() {
		return repoDB.getNumOfRepo();
	}

	@Override
	public int getNumOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getUsers(int page, int numPerPage, UserSortSandard sortStandard)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchRepository(RepositorySearchParam params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchUser(UserSearchParam params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static MassiveDataGetterNetwork getInstance() {
		return instance;
	}

}
