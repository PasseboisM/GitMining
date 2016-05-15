package network.data;

import network.service.GHDataSource;
import network.service.GHRelatedDataSource;
import network.service.MassiveDataSource;
import network.service.NetworkConnectionTester;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class BasicNetworkServiceFactory extends NetworkServiceFactory {

	@Override
	public SpecificDataSource getSpecificDataSource() {
		return new SpecificDataSourceDefault();
	}

	@Override
	public MassiveDataSource getMassiveDataSource() {
		return new MassiveDataSourceDefault();
	}

	@Override
	public NetworkConnectionTester getNetworkConnectionTester() {
		return new NetworkConnectionTesterDefault();
	}

	@Override
	public GHDataSource getGHDataSource() {
		return new GHDataSourceDefault();
	}

	@Override
	public GHRelatedDataSource getGHRelatedDataSource() {
		return new GHRelatedDataSourceDefault();
	}

}
