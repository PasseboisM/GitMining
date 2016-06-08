package data.manage;

import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import data.service.RecommendDataGetter;
import network.service.AnalysisDataSource;
import network.service.NetworkServiceFactory;

/**
 * @author River
 */
public class RecommendDataGetterDefault extends RecommendDataGetter{
	
	private static RecommendDataGetter instance = new RecommendDataGetterDefault();
	private AnalysisDataSource analysis = null;

	public RecommendDataGetterDefault() {
		NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
		analysis = factory.getAnalysisDataSource();
	}
	
	public List<String> getRecommendRepos(Language language) throws NetworkException{
		return analysis.recommendRepositories(language);
	}
	
	public List<String> getRecommendUsers(Language language) throws NetworkException{
		return analysis.recommendUsers(language);
	}
	public static RecommendDataGetter getInstance() {
		return instance;
	}
}
