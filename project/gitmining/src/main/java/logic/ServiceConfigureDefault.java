package logic;

import common.exception.NetworkException;
import data.service.DataServiceFactory;
import logic.service.ServiceConfigure;

public class ServiceConfigureDefault implements ServiceConfigure {
	
	@Override
	public void setOnlineActive(boolean useOnlineMode) throws NetworkException {
		if(DataServiceFactory.isUsingNetwork()==useOnlineMode) {
			return;
		} else {
			try {
				DataServiceFactory.tryUseNetwork(useOnlineMode);
			} catch (NetworkException e) {
				e.printStackTrace();
				throw e;
			}
		}

	}	
}
