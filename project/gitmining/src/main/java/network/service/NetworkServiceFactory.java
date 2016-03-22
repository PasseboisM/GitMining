package network.service;

import network.data.BasicNetworkServiceFactory;

/**
 * 
 * @author xjh14
 * 采用抽象工厂模式，提供网络层服务对象
 */
public abstract class NetworkServiceFactory {

	
	public abstract SpecificDataSource getSpecificDataSource();
	
	public abstract MassiveDataSource getMassiveDataSource();
	
	public abstract NetworkConnectionTester getNetworkConnectionTester();
	
	public static NetworkServiceFactory getInstance() {
		return new BasicNetworkServiceFactory();
	}
	
	
}
