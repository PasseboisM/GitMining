package data.manage;

import chart_data.service.GeneralStatisticsService;
import chart_data.service.RepositoryStatisticsService;
import chart_data.service.UserStatisticsService;
import data.manage.statistic.GeneralStatGetterNetwork;
import data.manage.statistic.RepositoryStatGetterNetwork;
import data.manage.statistic.UserStatGetterNetwork;
import data.service.StatDataMakerFactory;

public class StatDataMakerFactoryDefault implements StatDataMakerFactory {

	@Override
	public GeneralStatisticsService getGeneralStatistics() {
		return new GeneralStatGetterNetwork();
	}

	@Override
	public UserStatisticsService getUserStatistics() {
		return new UserStatGetterNetwork();
	}

	@Override
	public RepositoryStatisticsService getRepositoryStatistics() {
		return new RepositoryStatGetterNetwork();
	}

}
