package logic;

import logic.service.ServiceConfigure;
import network.service.NetworkServiceFactory;

public class ServiceConfigureDefault implements ServiceConfigure {
	
	@Override
	public boolean checkNetwork() {
		return NetworkServiceFactory.getInstance().getNetworkConnectionTester().testNetwork();
	}	

}
