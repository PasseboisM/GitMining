package logic;

import logic.calc.StatisticsMakerDefault;
import logic.data.GeneralGetterDefault;
import logic.data.RecommenderDefault;
import logic.data.RepoRelatedListGetterDefault;
import logic.data.SearchServiceDefault;
import logic.data.UserRelatedListGetterDefault;
import logic.service.LogInHelper;
import logic.service.StatisticsMaker;
import logic.service.UserRelatedListGetter;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.Recommender;
import logic.service.RepoRelatedListGetter;
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
	ServiceConfigure configure = new ServiceConfigureDefault();
	StatisticsMaker statistics = new StatisticsMakerDefault();
	SearchService search = new SearchServiceDefault();
	RepoRelatedListGetter repoRelatedListGetter = new RepoRelatedListGetterDefault();
	UserRelatedListGetter userRelatedListGetter = new UserRelatedListGetterDefault();
	Recommender recommender = new RecommenderDefault();
	
	public GeneralGetter getGeneralGetter() {
		return getter;
	}

	public SearchService getSearchService() {
		return search;
	}

	public StatisticsMaker getStatisticsMaker() {
		return statistics;
	}

	@Override
	public ServiceConfigure getServiceConfigure() {
		return configure;
	}

	@Override
	public RepoRelatedListGetter getRepoRelatedListGetter() {
		return repoRelatedListGetter;
	}
	
	@Override
	public UserRelatedListGetter getUserRelatedListGetter() {
		return userRelatedListGetter;
	}
	
	@Override
	public Recommender getRecommender() {
		return recommender;
	}
	@Override
	public LogInHelper getLogInHelper() {
		// TODO 稍后再写出 LogInHelper实现
		return null;
	}


}
