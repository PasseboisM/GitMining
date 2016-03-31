package logic.calc.service;

import chart_data.UserDistOverFollower;
import chart_data.RepoDistOverFork;
import chart_data.RepoDistOverLanguage;
import chart_data.RepoDistOverCreateTime;
import chart_data.RepoDistOverStar;
import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverType;


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
	public UserDistOverFollower getUserDistOverFollower();
	
	/**
	 * 获取当前系统中所有仓库的Fork数分布。
	 */
	public RepoDistOverFork getRepoDistOverFork();
	
	/**
	 * 获取所有项目的（主）语言分布情况
	 */
	public RepoDistOverLanguage getRepoDistOverLanguage();
	
	/**
	 * 获取所有仓库关于创建时间的分布（按年分组）
	 */
	public RepoDistOverCreateTime getRepoDistOverCreateTime();
	
	/**
	 * 获取所有用户关于创建时间的分布（按年分组）
	 */
	public UserDistOverCreateTime getUserDistOverCreateTime();
	
	/**
	 * 获取所有仓库关于其StarGazer数目的分布
	 */
	public RepoDistOverStar getRepoDistOverStar();
	
	/**
	 * 获取抽象GitUser关于其类型的分布（User/Organization）
	 */
	public UserDistOverType getUserDistOverType();
	
}
