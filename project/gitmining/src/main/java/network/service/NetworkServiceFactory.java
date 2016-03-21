package network.service;

import network.api.service.ApiMakerService;
import network.api.service.NetworkTester;
import network.data.BasicNetworkServiceFactory;

/**
 * 
 * @author xjh14
 * 采用抽象工厂模式，提供网络层服务对象
 */
public abstract class NetworkServiceFactory {

	
	public abstract SpecificDataSource getSpecificDataSource();
	
	public abstract MassiveDataSource getMassiveDataSource();
	
	public static NetworkServiceFactory getInstance() {
		return new BasicNetworkServiceFactory();
	}
	
	/**
	 * 网络状况能否支持网络层工作。
	 * 系统初始化时可以调用一次了解网络层能否提供服务；
	 * 当网络层抛出异常时，可以调用一次了解是否网络环境在程序运行时发生了变化，不能工作。
	 * @return 返回true则网络层可以工作
	 */
	public boolean testNetwork() {
		NetworkTester[] testers = ApiMakerService.getNetworkTesters();
		for(NetworkTester tester:testers) {
			if(!tester.test()) {
				return false;
			}
		}
		return true;
	}
}
