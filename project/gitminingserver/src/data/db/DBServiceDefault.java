package data.db;

import data.db.service.DBAnalysisService;
import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBStatService;
import data.db.service.DBUserService;



public class DBServiceDefault extends DBService {

	private static DBServiceDefault instance = new DBServiceDefault();
	
	private DBUserService userService = new DBUserServiceDefault();
	private DBRepoService repoService = new DBRepoServiceDefault();
	private DBAnalysisService analysisService = new DBAnalysisServiceDefault();
	
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
		// TODO 暂时不需要此功能
		return null;
	}

	@Override
	public DBAnalysisService getAnalysisService() {
		return analysisService;
	}
	
	public static DBServiceDefault getInstance() {
		return instance;
	}

}
