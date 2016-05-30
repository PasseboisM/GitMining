package data.service;

import data.DataServiceFactoryNetwork;

/**
 * @author xjh14
 */
public abstract class DataServiceFactory {
	
	/**
	 * Not yet implemented.
	 */
	public abstract MassiveDataGetter getMassiveDataGetter();
	
	/**
	 * Not yet implemented.
	 */
	public abstract SpecificDataGetter getSpecificDataGetter();
	
	/**
	 * Not yet implemented.
	 */
	public abstract UserListDataGetter getUserListDataGetter();
	
	/**
	 * Partly implemented.
	 */
	public abstract StatDataMakerFactory getStatDataMakerFactory();
	
	/**
	 * Not yet implemented.
	 */
	public abstract RecommendDataGetter getRecommendDataGetter();
	
	public static DataServiceFactory getInstance() {
		return new DataServiceFactoryNetwork();
	}
	
}
