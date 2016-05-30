package data.manage;

import java.util.Arrays;
import java.util.List;

import org.junit.experimental.categories.Categories;

import com.google.gson.Gson;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.Repository;
import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.service.MassiveDataGetter;
import network.service.GHDataSource;
import network.service.NetworkServiceFactory;

public class MassiveDataGetterNetwork extends MassiveDataGetter {

	private static MassiveDataGetterNetwork instance = new MassiveDataGetterNetwork();
	private GHDataSource networkSource = NetworkServiceFactory.getInstance().getGHDataSource();
	private DBRepoService repoDB = DBService.getInstance().getRepoService();
	
	@Override
	public List<String> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard)
			throws IndexOutOfBoundsException {
		return repoDB.getRepositories(page, numPerPage, sortStandard);
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
		//TODO More than DB source, data from GH should be included.
		
		
		
		
		
		return repoDB.searchRepository(params);
	}

	@Override
	public List<String> searchUser(UserSearchParam params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static MassiveDataGetterNetwork getInstance() {
		return instance;
	}

	public static void main(String[] args) throws NetworkException {
		/**
		 * TODO Sleep for 40 seconds? We need an overtime parameter!
		 */
		MassiveDataGetterNetwork mass = new MassiveDataGetterNetwork();
		Language[] ll = new Language[1];
		ll[0] = Language.JAVA;
		Category[] cl = new Category[1];
		cl[0] = Category.ALL;
		String[] sl = new String[1];
		sl[0] = "jquery";
		
		List<Repository> list = mass.networkSource.searchRepository(
				new RepositorySearchParam(ll,cl,sl,RepoSortStadard.NO_SORT));
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(list.get(0)));
		
	}
}
