package logic.service;

import logic.calc.service.GeneralStatisticsService;
import logic.calc.service.RepositoryStatisticsService;
import logic.calc.service.UserStatisticsService;

/**
 * 生成提供数据统计服务的接口。<br />
 * 通过本接口可以分别获得分析某一仓库的、分析某一用户的、分析总体数据的接口。
 * @author xjh14
 */
public interface StatisticsMaker {

	public GeneralStatisticsService getGeneralStatistics();
	
	public UserStatisticsService getUserStatistics();
	
	public RepositoryStatisticsService getRepositoryStatistics();
}
