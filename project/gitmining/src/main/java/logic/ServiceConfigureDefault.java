package logic;

import logic.service.ServiceConfigure;

public class ServiceConfigureDefault implements ServiceConfigure {
	
	@Override
	public boolean checkNetwork() {
		//TODO
		return true;
	}	
}
