package server.data.service;

import server.data.manage.DataServiceMakerDefault;

public abstract class DataServiceMaker {

	private static DataServiceMaker instance = new DataServiceMakerDefault();
	
	public abstract RepositoryRequestHandler getRepositoryService();
	
	public abstract UserRequestHandler getUserService();
	
	public abstract StatisticRequestHandler getStatisticHandler();
	
	public abstract RecommendRequestHandler getRecommendHandler();
	
	public static DataServiceMaker getInstance() {
		return instance;
	}
}
