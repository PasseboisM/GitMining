package data.service;

import data.manage.DataServiceMakerDefault;

public abstract class DataServiceMaker {

	private static DataServiceMaker instance = new DataServiceMakerDefault();
	
	public abstract RepositoryRequestHandler getRepositoryService();
	
	public abstract UserRequestHandler getUserService();
	
	public abstract StatiticRequestHandler getStatisticHandler();
	
	public DataServiceMaker getInstance() {
		return instance;
	}
}
