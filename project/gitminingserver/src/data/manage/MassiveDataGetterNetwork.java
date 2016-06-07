package data.manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.junit.experimental.categories.Categories;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortStandard;
import common.exception.NetworkException;
import common.exception.TargetNotFoundException;
import common.model.beans.GitUserBeans;
import common.model.beans.RepositoryBeans;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;
import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBUserService;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import network.service.GHDataSource;
import network.service.NetworkServiceFactory;

public class MassiveDataGetterNetwork extends MassiveDataGetter {

	private static MassiveDataGetterNetwork instance = new MassiveDataGetterNetwork();
	
	
	private GHDataSource networkSource = NetworkServiceFactory.getInstance().getGHDataSource();
	private DBRepoService repoDB = DBService.getInstance().getRepoService();
	private DBUserService userDB = DBService.getInstance().getUserService();
	
	private SpecificDataGetter spec = DataServiceFactory.getInstance().getSpecificDataGetter();
	
	private Gson gson = new Gson();
	
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
		return userDB.getNumOfUser();
	}

	@Override
	public List<String> getUsers(int page, int numPerPage, UserSortStandard sortStandard)
			throws IndexOutOfBoundsException {
		return userDB.getUsers(page, numPerPage, sortStandard);
	}

	@Override
	public List<String> searchRepository(RepositorySearchParam params) {
		final int MAX_RETURN_NUM = 200;
		
		List<Repository> repos = new ArrayList<Repository>(200);
		List<String> result = new ArrayList<String>(200);
		
		try {
			repos.addAll(networkSource.searchRepository(params));
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		List<String> fromDB = repoDB.searchRepository(params);
		Iterator<String> iter = fromDB.iterator();
		while (iter.hasNext()) {
			Repository temp = gson.fromJson(iter.next(), RepositoryBeans.class);
			for (Repository r:repos) {
				if (temp.getFull_name().equals(r.getFull_name())) {
					iter.remove();
				}
			}
		}
		
		for (String s:fromDB) {
			repos.add(gson.fromJson(s, RepositoryBeans.class));
		}
		
		repos.sort(params.getSortStandard().getComparator());
		
		for (int i=0;i<MAX_RETURN_NUM&&i<repos.size();i++) {
			result.add(gson.toJson(repos.get(i)));
		}
		
		return result;
	}

	@Override
	public List<String> searchUser(UserSearchParam params) {
		final int MAX_RETURN_NUM = 200;
		
//		List<GitUser> partialUser = new ArrayList<>(MAX_RETURN_NUM);
//		List<GitUser> users = new ArrayList<GitUser>(MAX_RETURN_NUM);
//		List<String> result = new ArrayList<String>(MAX_RETURN_NUM);
//		
//		try {
//			partialUser.addAll(networkSource.searchUser(params));
//		} catch (NetworkException e) {
//			e.printStackTrace();
//		}
//		for (GitUser u:partialUser) {
//			try {
//				users.add(gson.fromJson(spec.getSpecificGitUser(u.getLogin()),GitUserBeans.class));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		List<String> usersFromDB = userDB.searchUsers(params);
//		Iterator<String> iter = usersFromDB.iterator();
//		while (iter.hasNext()) {
//			GitUser temp = gson.fromJson(iter.next(), GitUserBeans.class);
//			for (GitUser user:users) {
//				if (user.getLogin().equals(temp.getLogin())) {
//					iter.remove();
//					break;
//				}
//			}
//		}
//		
//		for (String s:usersFromDB) {
//			users.add(gson.fromJson(s, GitUserBeans.class));
//		}
//		
//		users.sort(params.getSortStandard().getComparator());
//		
//		for (GitUser user:users) {
//			result.add(gson.toJson(user));
//		}
		
		List<String> result = userDB.searchUsers(params);
		
		return result;
	}
	
	public static MassiveDataGetterNetwork getInstance() {
		return instance;
	}

//	public static void main(String[] args) throws NetworkException {
//		/**
//		 * TODO Sleep for 40 seconds? We need an overtime parameter!
//		 */
//		MassiveDataGetterNetwork mass = new MassiveDataGetterNetwork();
//		Language[] ll = new Language[1];
//		ll[0] = Language.JAVA_SCRIPT;
//		Category[] cl = new Category[1];
//		cl[0] = Category.ALL;
//		String[] sl = new String[1];
//		sl[0] = "jquery";
//		long stime = System.currentTimeMillis();
//		List<String> list = mass.searchRepository(
//				new RepositorySearchParam(ll,cl,sl,RepoSortStadard.STARS_DESCENDING));
//		System.out.println("Time:"+(System.currentTimeMillis()-stime));
//		System.out.println("Num of got:"+list.size());
//		Gson gson = new Gson();
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
//	}
}
