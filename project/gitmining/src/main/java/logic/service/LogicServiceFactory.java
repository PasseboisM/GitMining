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
	 * TODO 确定接口内容后填写注释
	 * @return
	 */
	public abstract StatisticsMaker getChartMaker();
	
	/**
	 * 获取对逻辑层服务进行设置的对象。
	 * @return
	 */
	public abstract ServiceConfigure getServiceConfigure();
	
	/**
	 * 获取本接口默认实现。
	 * @return 可用于获取各种逻辑层服务的LogicServiceFactory对象
	 */
	public static LogicServiceFactory getInstance() {
		return new BasicLogicServiceFactory();
	}
}
