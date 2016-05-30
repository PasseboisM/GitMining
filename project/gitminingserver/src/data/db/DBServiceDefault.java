package data.db;

import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBStatService;
import data.db.service.DBUserService;

public class DBServiceDefault extends DBService {

	private static DBServiceDefault instance = new DBServiceDefault();
	
	private DBRepoService repoService = new DBRepoServiceDefault();
	
	@Override
	public DBUserService getUserService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBRepoService getRepoService() {
		// TODO Auto-generated method stub
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
