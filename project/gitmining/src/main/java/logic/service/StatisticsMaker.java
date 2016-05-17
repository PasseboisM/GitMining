package logic.service;

import chart_data.service.GeneralStatisticsService;
import chart_data.service.RepositoryStatisticsService;
import chart_data.service.UserStatisticsService;

/**
 * 生成提供数据统计服务的接口。<br />
 * 通过本接口可以分别获得分析某一仓库的、分析某一用户的、分析总体数据的接口。
 * @author xjh14
 */
public interface StatisticsMaker {

	/**
	 * 获取可用于对整体数据（全体Repo/User）进行特征分析、取得结果的接口
	 */
	public GeneralStatisticsService getGeneralStatistics();
	
	/**
	 * 获取可对单个GitUser进行数据特征分析、取得结果的接口
	 */
	public UserStatisticsService getUserStatistics();
	
	/**
	 * 获取可对单个Repository进行数据特征分析、取得结果的接口
	 */
	public RepositoryStatisticsService getRepositoryStatistics();
}
