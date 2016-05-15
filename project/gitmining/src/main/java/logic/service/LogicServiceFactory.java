package logic.service;

import logic.BasicLogicServiceFactory;

/**
 * 
 * @author xjh14
 * 采用抽象工厂模式，为界面层提供逻辑层服务对象
 * TODO 注释没写
 * 
 */
public abstract class LogicServiceFactory {
	
	/**
	 * 获取可用于笼统查询大量Repository/GitUser等数据的接口
	 * @return 提供笼统查询的GeneralGetter接口
	 */
	public abstract GeneralGetter getGeneralGetter();
	
	/**
	 * 获取可用于进行搜索特定Repository/GitUser数据集合的接口
	 * @return 提供搜索功能的SearchService接口
	 */
	public abstract SearchService getSearchService();
	
	/**
	 * 获取可用于进行整体（全体Repo/User）/单个对象（单个Repo/User）数据特征分析的接口
	 * @return 用于进一步得到更详细接口的StatisticsMaker
	 */
	public abstract StatisticsMaker getStatisticsMaker();
	
	/**
	 * 获取可以进行用户登录的接口
	 * @return 提供登录功能的接口LogInHelper
	 */
	public abstract LogInHelper getLogInHelper();
	
	/**
	 * 获取对逻辑层服务进行设置的对象。
	 * @return
	 */
	public abstract ServiceConfigure getServiceConfigure();
	public abstract RepoRelatedListGetter getRepoRelatedListGetter();
	public abstract UserRelatedListGetter getUserRelatedListGetter();
	/**
	 * 获取本接口默认实现。
	 * @return 可用于获取各种逻辑层服务的LogicServiceFactory对象
	 */
	public static LogicServiceFactory getInstance() {
		return new BasicLogicServiceFactory();
	}
}
