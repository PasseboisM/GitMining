package data.service;

import common.exception.NetworkException;
import data.DataServiceFactoryNetwork;
import network.service.NetworkServiceFactory;

/**
 * 
 * @author xjh14
 * 
 * 为Data层外层提供的获取Data服务的接口
 * 
 * TODO 设计Data层服务接口并填在本接口内
 * 
 */
public abstract class DataServiceFactory {
	
	private volatile static boolean networkAvailable = false;
	private volatile static boolean isUsingNetwork = false;
	
	public abstract MassiveDataGetter getMassiveDataGetter();
	
	public abstract SpecificDataGetter getSpecificDataGetter();
	
	public abstract UserListDataGetter getUserListDataGetter();
	
	public abstract StatDataMakerFactory getStatDataMakerFactory();
	
	public abstract RecommendDataGetter getRecommendDataGetter();
	
	private static boolean getNetworkAvailability() {
		networkAvailable = NetworkServiceFactory.getInstance().getNetworkConnectionTester().testNetwork();
		return networkAvailable;
	}
	
	public static DataServiceFactory getInstance() {
		return new DataServiceFactoryNetwork();
	}
	
	public static boolean tryUseNetwork(boolean useNetwork) throws NetworkException {
		if(!useNetwork) {
			isUsingNetwork = false;
			return true;
		}
		
		if (getNetworkAvailability()) {
			isUsingNetwork = true;
			return true;
		} else {
			throw new NetworkException();
		}
	}
	
	public static boolean isUsingNetwork() {
		return isUsingNetwork;
	}
	
}
