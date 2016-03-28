package logic.calc.service;

import common.service.GitUser;

import chart_data.radar.UserRanks;

/**
 * 用于为单个用户进行数据统计分析的服务接口。<br />
 * 一般接受的参数是一个GitUser对象。
 * @author xjh14
 * Ver: 0.1
 * Created at: 2016年3月27日
 */
public interface UserStatisticsService {

	/**
	 * 获取指定GitUser综合评分
	 * @param u 待分析的GitUser对象
	 * @return UserRanks 包含此用户评分的对象
	 */
	public UserRanks getRanks(GitUser u);
	
	
}
