package data;

import data.service.DataServiceFactory;
import data.service.UserListDataGetter;
import data.service.MassiveDataGetter;
import data.service.RecommendDataGetter;
import data.service.RepoListDataGetter;
import data.service.SpecificDataGetter;

public class BasicDataServiceFactory extends DataServiceFactory {

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
	public RepoListDataGetter getRepoListDataGetter() {
		return RepoListDataGetter.getInstance();
	}

	@Override
	public RecommendDataGetter getRecommendDataGettet() {
		return RecommendDataGetter.getInstance();
	}

}
