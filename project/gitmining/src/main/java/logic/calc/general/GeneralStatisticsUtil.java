package logic.calc.general;

import chart_data.FollowerNumberRanges;
import chart_data.ForkNumberRanges;
import chart_data.LanguageCounts;
import chart_data.RepoCreateOnTimeCounts;
import chart_data.StarCountRanges;
import chart_data.UserCreateOnTimeCounts;
import chart_data.UserTypeCounts;
import logic.calc.service.GeneralStatisticsService;

public class GeneralStatisticsUtil implements GeneralStatisticsService{

	@Override
	public FollowerNumberRanges getFollowerNumberRanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForkNumberRanges getForkNumberRanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageCounts getLanguageCounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepoCreateOnTimeCounts getRepoCreateOnTimeCounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserCreateOnTimeCounts getUserCreateOnTimeCounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarCountRanges getStarCountRanges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTypeCounts getUserTypeCounts() {
		// TODO Auto-generated method stub
		
		UserTypeCounts userTypeCounts = new UserTypeCounts();
		userTypeCounts.addCount("User", 27067);
		userTypeCounts.addCount("Organization", 950);
		return userTypeCounts;
		
//		return null
	}

}
