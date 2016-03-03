package logic.service;

import logic.BasicLogicServiceFactory;

/**
 * 
 * @author River
 * 采用抽象工厂模式，为界面层提供逻辑层服务对象
 * TODO 注释没写
 * 
 */
public abstract class LogicServiceFactory {
	

	public abstract GeneralGetter getGeneralGetter();
	
	public abstract SearchService getSearchService();
	
	public abstract ChartMaker getChartMaker();
	
	public static LogicServiceFactory getInstance() {
		return new BasicLogicServiceFactory();
	}
}
