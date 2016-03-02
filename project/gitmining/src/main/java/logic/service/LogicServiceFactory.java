package logic.service;

/**
 * 
 * @author River
 * 采用工厂模式，为界面层提供逻辑层服务对象
 * @TODO doc
 */
public interface LogicServiceFactory {
	
	public GeneralGetter getGeneralGetter();
	
	public SearchService getSearchService();
	
	public ChartMaker getChartMaker();
}
