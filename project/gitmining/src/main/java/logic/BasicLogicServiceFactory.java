package logic;

import logic.data.GeneralGetterDefault;
import logic.service.ChartMaker;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.SearchService;

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
		// TODO Auto-generated method stub
		return null;
	}

	public ChartMaker getChartMaker() {
		// TODO Auto-generated method stub
		return null;
	}

}
