package data.db;

import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBStatService;
import data.db.service.DBUserService;

public class DBServiceDefault extends DBService {

	private static DBServiceDefault instance = new DBServiceDefault();
	
	@Override
	public DBUserService getUserService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBRepoService getRepoService() {
		// TODO Auto-generated method stub
		return null;
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
