package data;

import data.manage.StatDataMakerFactoryNetwork;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.RecommendDataGetter;
import data.service.SpecificDataGetter;
import data.service.StatDataMakerFactory;
import data.service.UserListDataGetter;

public class DataServiceFactoryNetwork extends DataServiceFactory {

	@Override
	public MassiveDataGetter getMassiveDataGetter() {
		return MassiveDataGetter.getInstance();
	}

	@Override
	public SpecificDataGetter getSpecificDataGetter() {
		return SpecificDataGetter.getInstance();
	}

	@Override
	public UserListDataGetter getUserListDataGetter() {
		return UserListDataGetter.getInstance();
	}

	@Override
	public RecommendDataGetter getRecommendDataGetter() {
		return RecommendDataGetter.getInstance();
	}

	@Override
	public StatDataMakerFactory getStatDataMakerFactory() {
		return StatDataMakerFactoryNetwork.getInstance();
	}

}
