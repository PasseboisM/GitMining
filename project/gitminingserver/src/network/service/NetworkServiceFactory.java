package network.service;

import network.data.GHNetworkServiceFactory;

/**
 * 
 * @author xjh14
 * 采用抽象工厂模式，提供网络层服务对象
 */
public abstract class NetworkServiceFactory {

	
	public abstract SpecificDataSource getSpecificDataSource();
	
	@Deprecated
	public abstract MassiveDataSource getMassiveDataSource();
	
	public abstract NetworkConnectionTester getNetworkConnectionTester();
	
	public abstract GHDataSource getGHDataSource();
	
	public abstract UserRelatedDataSource getUserRelatedDataSource();
	
	public abstract RepoRelatedDataSource getRepoRelatedDataSource();
	
	public abstract AnalysisDataSource getAnalysisDataSource();
	
	public static NetworkServiceFactory getInstance() {
//		return new BasicNetworkServiceFactory();
		return new GHNetworkServiceFactory();
	}
	
}
