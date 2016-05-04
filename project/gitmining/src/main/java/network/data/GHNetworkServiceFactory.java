package network.data;

import network.service.MassiveDataSource;
import network.service.NetworkConnectionTester;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class GHNetworkServiceFactory extends NetworkServiceFactory {

	@Override
	public SpecificDataSource getSpecificDataSource() {
		return new GHSpecificDataSource();
	}

	@Override
	public MassiveDataSource getMassiveDataSource() {
		return null;
	}

	@Override
	public NetworkConnectionTester getNetworkConnectionTester() {
		// TODO Auto-generated method stub
		return null;
	}

}
