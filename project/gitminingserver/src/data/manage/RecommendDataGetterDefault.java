package data.manage;

import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;
import data.service.RecommendDataGetter;
import network.service.AnalysisDataSource;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

/**
 * 
 * @author River
 * TODO 将原先代码进行少量修改即可达到返回String的效果
 */
public class RecommendDataGetterDefault extends RecommendDataGetter{
	private static RecommendDataGetter instance = new RecommendDataGetterDefault();
	private AnalysisDataSource analysis = null;
	private SpecificDataSource specific = null;
	public RecommendDataGetterDefault() {
		NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
		analysis = factory.getAnalysisDataSource();
		specific = factory.getSpecificDataSource();
	}
	
	public List<String> getRecommendRepos(Language language) throws NetworkException{
		List<Repository> repositories = new ArrayList<>();
		List<String> recommendRepos = analysis.recommendRepositories(language);
		for (String fullName : recommendRepos) {
			repositories.add(specific.getSpecificRepo(fullName));
		}
//		return repositories;
		return null;
	}
	
	public List<String> getRecommendUsers(Language language) throws NetworkException{
		List<GitUser> users = new ArrayList<>();
		List<String> recommendUsers = analysis.recommendUsers(language);
		for (String login : recommendUsers) {
			users.add(specific.getSpecificUser(login));
		}
//		return users;
		return null;
	}
	public static RecommendDataGetter getInstance() {
		return instance;
	}
}
