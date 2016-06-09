package data.service;

import data.DataServiceFactoryNetwork;

/**
 * @author xjh14
 */
public abstract class DataServiceFactory {
	
	public abstract MassiveDataGetter getMassiveDataGetter();
	
	public abstract SpecificDataGetter getSpecificDataGetter();
	
	public abstract UserListDataGetter getUserListDataGetter();
	
	public abstract StatDataMakerFactory getStatDataMakerFactory();

	public abstract RecommendDataGetter getRecommendDataGetter();
	
	public abstract LogInDataService getLogInDataService();
	
	public static DataServiceFactory getInstance() {
		return new DataServiceFactoryNetwork();
	}
	
}
