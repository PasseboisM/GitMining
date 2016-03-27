package logic.calc.service;

import chart_data.FollowerNumberRanges;
import chart_data.ForkNumberRanges;
import chart_data.LanguageCounts;


/**
 * 提供对本系统内所有数据进行整体特征分析的服务接口。<br />
 * 一般不涉及对某一具体的Repository，GitUser的分析。
 * @author xjh14
 * Ver: 0.1
 * Created at: 2016年3月27日
 */
public interface GeneralStatisticsService {

	/**
	 * 获取当前系统中所有用户的Follower数目分布。
	 */
	public FollowerNumberRanges getFollowerNumberRanges();
	
	/**
	 * 获取当前系统中所有仓库的Fork数分布。
	 */
	public ForkNumberRanges getForkNumberRanges();
	
	/**
	 * 获取
	 */
	public LanguageCounts getLanguageCounts();
}
