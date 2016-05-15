package data.manage;

import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import data.service.MassiveDataGetter;
import data.service.RecommendDataGetter;
import network.service.AnalysisDataSource;
import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class RecommendDataGetterDefault extends RecommendDataGetter{
	private static RecommendDataGetter instance = new RecommendDataGetterDefault();
	private AnalysisDataSource analysis = null;
	private SpecificDataSource specific = null;
	public RecommendDataGetterDefault() {
		NetworkServiceFactory factory = NetworkServiceFactory.getInstance();
		analysis = factory.getAnalysisDataSource();
		specific = factory.getSpecificDataSource();
	}
	
	public List<Repository> getRecommendRepos(Language language) throws NetworkException{
		List<Repository> repositories = new ArrayList<>();
		List<String> recommendRepos = analysis.recommendRepositories(language);
		recommendRepos.forEach(fullName->{
				try {
					repositories.add(specific.getSpecificRepo(fullName));
				} catch (Exception e) {
					System.out.println("no such repo:"+fullName);
					e.printStackTrace();
				}
		});
		return repositories;
	}
	public static RecommendDataGetter getInstance() {
		return instance;
	}
}
