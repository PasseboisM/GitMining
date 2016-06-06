package data.manage.statistic;

import com.google.gson.Gson;

import calc.service.CalcStatService;
import calc.service.RepoStatService;
import calc.service.UserStatService;
import chart_data.radar.UserRanks;
import common.exception.TargetNotFoundException;
import common.model.beans.GitUserBeans;
import common.service.GitUser;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import data.service.stat.UserStatGetter;

public class UserStatGetterNetwork implements UserStatGetter {

	private UserStatService userCalc =
			CalcStatService.getInstance().getUserStatService();
	private SpecificDataGetter specificData =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	private Gson gson = new Gson();
	
	
	@Override
	public String getUserRanks(String login) throws TargetNotFoundException {
		String user = specificData.getSpecificGitUser(login);
		GitUser processed = gson.fromJson(user, GitUserBeans.class);
		UserRanks ranks = userCalc.getRanks(processed);
		return gson.toJson(ranks);
	}


}
