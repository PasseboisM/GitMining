package data.service;

import data.DataServiceFactoryNetwork;

/**
 * 提供服务器各个详细数据接口
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
