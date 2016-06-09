package data.db.service;

import data.db.DBServiceDefault;

public abstract class DBService {

	public abstract DBUserService getUserService();
	
	public abstract DBRepoService getRepoService();
	
	public abstract DBStatService getStatService();
	
	public abstract DBAnalysisService getAnalysisService();
	
	public static DBService getInstance() {
		return DBServiceDefault.getInstance();
	}
}
