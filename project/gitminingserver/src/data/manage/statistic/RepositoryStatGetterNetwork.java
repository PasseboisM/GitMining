package data.manage.statistic;

import com.google.gson.Gson;

import calc.service.CalcStatService;
import calc.service.RepoStatService;
import chart_data.radar.RepositoryRanks;
import common.exception.TargetNotFoundException;
import common.model.beans.RepositoryBeans;
import common.service.Repository;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import data.service.stat.RepoStatGetter;

public class RepositoryStatGetterNetwork implements RepoStatGetter {

	private RepoStatService repoCalc = 
			CalcStatService.getInstance().getRepoStatService();
	private SpecificDataGetter specificData =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	private Gson gson = new Gson();
	
	@Override
	public String getRepoRanks(String fullName) throws TargetNotFoundException {
		String repository = specificData.getSpecificRepo(fullName);
		Repository processed = gson.fromJson(repository, RepositoryBeans.class);
		RepositoryRanks ranks = repoCalc.getRanks(processed);
		return gson.toJson(ranks);
	}


}
