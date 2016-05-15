package logic.data;

import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;
import data.service.DataServiceFactory;
import data.service.RecommendDataGetter;
import logic.service.Recommender;

public class RecommenderDefault implements Recommender{

	private RecommendDataGetter getter = null; 
	
	public RecommenderDefault() {
		getter = DataServiceFactory.getInstance().getRecommendDataGetter();
	}
	@Override
	public List<Repository> getRecommendRepos() throws NetworkException {
		//TODO Language to be analysis
		Language language = null;
		return getter.getRecommendRepos(language);
	}

	@Override
	public List<GitUser> getRecommendUsers() throws NetworkException {
		//TODO Language to be analysis
		Language language = null;
		return getter.getRecommendUsers(language);
	}

}
