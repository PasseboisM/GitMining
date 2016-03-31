package logic.calc.general;

import chart_data.UserDistOverFollower;
import chart_data.RepoDistOverFork;
import chart_data.RepoDistOverLanguage;
import chart_data.RepoDistOverCreateTime;
import chart_data.RepoDistOverStar;
import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverType;
import common.enumeration.attribute.Language;
import logic.calc.service.GeneralStatisticsService;

public class GeneralStatisticsUtil implements GeneralStatisticsService{

	@Override
	public UserDistOverFollower getUserDistOverFollower() {
		String pyFile = "user_dist_over_follower.py";
		
		UserDistOverFollower followerNumberRanges = new UserDistOverFollower();
		followerNumberRanges.addNewRange(0, 10, 9798);
		followerNumberRanges.addNewRange(10, 20, 1398);
		followerNumberRanges.addNewRange(20, 30, 758);
		followerNumberRanges.addNewRange(30, 40, 598);
		followerNumberRanges.addNewRange(40, 27069, 1998);
		return followerNumberRanges;
	}

	@Override
	public RepoDistOverFork getRepoDistOverFork() {
		String pyFile = "repo_dist_over_fork.py";
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepoDistOverLanguage getRepoDistOverLanguage() {
		String pyFile = "repo_dist_over_lang.py";
		
		//TODO 测试用的，写好了就删掉
		RepoDistOverLanguage languageCounts = new RepoDistOverLanguage();
		languageCounts.addLanguageCount(Language.C, 98);
		languageCounts.addLanguageCount(Language.C_PLUS_PLUS, 243);
		languageCounts.addLanguageCount(Language.C_SHARP, 34);
		languageCounts.addLanguageCount(Language.JAVA, 66);
		languageCounts.addLanguageCount(Language.COMMON_LISP, 109);
		languageCounts.addLanguageCount(Language.HTML, 21);
		languageCounts.addLanguageCount(Language.OBJECTIVE_C, 676);
		languageCounts.addLanguageCount(Language.PERL, 87);
		languageCounts.addLanguageCount(Language.RUBY, 555);
		languageCounts.addLanguageCount(Language.PYTHON, 27);
		languageCounts.addLanguageCount(Language.R, 78);
		return languageCounts;
//		return null;
	}

	@Override
	public RepoDistOverCreateTime getRepoDistOverCreateTime() {
		String pyFile = "repo_dist_over_create_time.py";
		
		//TODO 测试用的，写好了就删掉
		RepoDistOverCreateTime repoCreateOnTimeCounts = new RepoDistOverCreateTime();
		repoCreateOnTimeCounts.addCreateCount("2007", 9);
		repoCreateOnTimeCounts.addCreateCount("2008", 179);
		repoCreateOnTimeCounts.addCreateCount("2009", 997);
		repoCreateOnTimeCounts.addCreateCount("2010", 5932);		
		return repoCreateOnTimeCounts;
//		return null;
	}

	@Override
	public UserDistOverCreateTime getUserDistOverCreateTime() {
		String pyFile = "user_dist_over_create_time.py";
		
		UserDistOverCreateTime userCreateOnTimeCounts = new UserDistOverCreateTime();
		userCreateOnTimeCounts.addCreateCount("2007", 28);
		userCreateOnTimeCounts.addCreateCount("2008", 228);
		userCreateOnTimeCounts.addCreateCount("2009", 578);
		userCreateOnTimeCounts.addCreateCount("2010", 698);
		userCreateOnTimeCounts.addCreateCount("2011", 576);
		// TODO Auto-generated method stub
		return userCreateOnTimeCounts;
	}

	@Override
	public RepoDistOverStar getRepoDistOverStar() {
		String pyFile = "repo_dist_over_star.py";
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDistOverType getUserDistOverType() {
		String pyFile = "user_dist_over_type.py";
		//TODO 测试用的，写好了就删掉
		UserDistOverType userTypeCounts = new UserDistOverType();
		userTypeCounts.addCount("User", 27067);
		userTypeCounts.addCount("Organization", 950);
		return userTypeCounts;
		
//		return null
	}

}
