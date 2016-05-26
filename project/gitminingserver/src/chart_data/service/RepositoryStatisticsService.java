package chart_data.service;

import chart_data.radar.RepositoryRanks;
import common.service.Repository;


/**
 * 用于为单个仓库进行数据分析、统计的服务接口。<br />
 * 一般接受的参数为一个Repository。
 * @author xjh14
 * Ver: 0.1
 * Created at: 2016年3月27日
 */
public interface RepositoryStatisticsService {

	/**
	 * 获取某个Repository的综合评分
	 * @param r 待分析的Repository对象
	 * @return RepositoryRanks 内含此Repository综合评分的对象
	 */
	public RepositoryRanks getRanks(Repository r);
	
}
