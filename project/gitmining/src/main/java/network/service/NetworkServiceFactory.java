package network.service;

import java.io.IOException;

import org.kohsuke.github.GHRepository;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import network.data.BasicNetworkServiceFactory;
import network.data.GHNetworkServiceFactory;
import network.data.GHSpecificDataSource;

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
//		return new GHNetworkServiceFactory();
	}
	
//	public static void main(String[] args) throws NetworkException, DataCorruptedException, IOException {
//		for(int i=0;i<80;i++){
//			//80次测试
//			System.out.println("time for "+i);
//			NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
//			SpecificDataSource dataSource = factory.getSpecificDataSource();
//			try {
//				dataSource.getSpecificRepo("kohsuke/github-api");
//			} catch (NetworkException e) {
//				e.printStackTrace();
//			} catch (DataCorruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		GHSpecificDataSource source = new GHSpecificDataSource();
//		GHRepository repository = source.getSpecificRepo("kohsuke/github-api");
//		System.out.println(repository.getFullName());
		
//		SpecificDataSource source = NetworkServiceFactory.getInstance().getSpecificDataSource();
//		Repository repository = source.getSpecificRepo("kohsuke/github-api");
//		System.out.println(repository.getFull_name());
//	}
}
