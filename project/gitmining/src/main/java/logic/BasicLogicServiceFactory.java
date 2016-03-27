package logic;

import logic.data.GeneralGetterDefault;
import logic.data.SearchServiceDefault;
import logic.service.StatisticsMaker;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.SearchService;
import logic.service.ServiceConfigure;

/**
 * 
 * @author xjh14
 * 
 * 基础版逻辑服务工厂
 * 
 */
public class BasicLogicServiceFactory extends LogicServiceFactory {

	GeneralGetter getter = new GeneralGetterDefault();
	
	public GeneralGetter getGeneralGetter() {
		return getter;
	}

	public SearchService getSearchService() {
		return new SearchServiceDefault();
	}

	public StatisticsMaker getChartMaker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceConfigure getServiceConfigure() {
		return new ServiceConfigureDefault();
	}

}
