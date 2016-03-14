package logic;

import common.exception.NetworkException;
import data.service.DataServiceFactory;
import logic.service.ServiceConfigure;

public class ServiceConfigureDefault implements ServiceConfigure {

//	private DataServiceFactory dataService = null;
	
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

//	public ServiceConfigureDefault() {
//		dataService = DataServiceFactory.getInstance();
//	}
//	
}
