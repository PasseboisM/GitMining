package data.db;

import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBStatService;
import data.db.service.DBUserService;



public class DBServiceDefault extends DBService {

	private static DBServiceDefault instance = new DBServiceDefault();
	
	private DBUserService userService = new DBUserServiceDefault();
	private DBRepoService repoService = new DBRepoServiceDefault();
	
	
	@Override
	public DBUserService getUserService() {
		return userService;
	}

	@Override
	public DBRepoService getRepoService() {
		return repoService;
	}

	@Override
	public DBStatService getStatService() {
		// TODO Auto-generated method stub
		return null;
	}

	public static DBServiceDefault getInstance() {
		return instance;
	}
	
}
