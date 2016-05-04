package network.service;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import network.data.BasicNetworkServiceFactory;
import network.data.GHNetworkServiceFactory;

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
//		return new BasicNetworkServiceFactory();
		return new GHNetworkServiceFactory();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<80;i++){
			//80次测试
			System.out.println("time for "+i);
			NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
			SpecificDataSource dataSource = factory.getSpecificDataSource();
			try {
				dataSource.getSpecificRepo("kohsuke/github-api");
			} catch (NetworkException e) {
				e.printStackTrace();
			} catch (DataCorruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		for(int i=0;i<5010;i++){
			//5000次测试
			System.out.println("time for "+i);
			NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
			SpecificDataSource dataSource = factory.getSpecificDataSource();
			try {
				dataSource.getSpecificRepo("kohsuke/github-api");
			} catch (NetworkException e) {
				e.printStackTrace();
			} catch (DataCorruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
}
