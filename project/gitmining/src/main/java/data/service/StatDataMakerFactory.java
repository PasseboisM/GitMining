package data.service;

import chart_data.service.GeneralStatisticsService;
import chart_data.service.RepositoryStatisticsService;
import chart_data.service.UserStatisticsService;

public interface StatDataMakerFactory {

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
