package logic.calc;

import logic.calc.repo.RepoStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import logic.calc.service.RepositoryStatisticsService;
import logic.calc.service.UserStatisticsService;
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
		// TODO Auto-generated method stub
		return null;
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
