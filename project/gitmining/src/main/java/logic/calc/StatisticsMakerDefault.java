package logic.calc;

import chart_data.service.GeneralStatisticsService;
import chart_data.service.RepositoryStatisticsService;
import chart_data.service.UserStatisticsService;
import data.manage.statistic.GeneralStatGetterNetwork;
import logic.calc.repo.RepoStatisticsUtil;
import logic.calc.user.UserStatisticsUtil;
import logic.service.StatisticsMaker;

/**
 * StatisticsMaker的默认实现（用于迭代二）
 * @author xjh14
 * Ver: 0.1（尚未实现）
 * Created at: 2016年3月28日
 */
public class StatisticsMakerDefault implements StatisticsMaker {

	@Override
	public GeneralStatisticsService getGeneralStatistics() {
		return new GeneralStatGetterNetwork();
	}

	@Override
	public UserStatisticsService getUserStatistics() {
		return new UserStatisticsUtil();
	}

	@Override
	public RepositoryStatisticsService getRepositoryStatistics() {
		return new RepoStatisticsUtil();
	}

}
