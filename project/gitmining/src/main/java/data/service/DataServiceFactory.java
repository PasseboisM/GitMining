package data.service;

import common.exception.NetworkException;

import network.service.NetworkServiceFactory;
import data.BasicDataServiceFactory;

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
	
	public abstract ListDataGetter getListDataGetter();
	
	private static boolean getNetworkAvailability() {
		networkAvailable = NetworkServiceFactory.getInstance().getNetworkConnectionTester().testNetwork();
		return networkAvailable;
	}
	
	public static DataServiceFactory getInstance() {
		return new BasicDataServiceFactory();
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
