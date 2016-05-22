package network.data;

import network.service.AnalysisDataSource;
import network.service.GHDataSource;
import network.service.MassiveDataSource;
import network.service.NetworkConnectionTester;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;
import network.service.UserRelatedDataSource;

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
	public UserRelatedDataSource getUserRelatedDataSource() {
		return new UserRelatedDataSourceDefault();
	}

	/*@Override
	public RepoRelatedDataSource getRepoRelatedDataSource() {
		return new RepoRelatedDataSourceDefault();
	}*/

	@Override
	public AnalysisDataSource getAnalysisDataSource() {
		return new GHTAnalysisDataSourceDefault();
	}

}
