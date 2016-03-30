package logic.calc.general;

import chart_data.FollowerNumberRanges;
import chart_data.ForkNumberRanges;
import chart_data.LanguageCounts;
import chart_data.RepoCreateOnTimeCounts;
import chart_data.StarCountRanges;
import chart_data.UserCreateOnTimeCounts;
import chart_data.UserTypeCounts;
import common.enumeration.attribute.Language;
import logic.calc.service.GeneralStatisticsService;

public class GeneralStatisticsUtil implements GeneralStatisticsService{

	@Override
	public FollowerNumberRanges getFollowerNumberRanges() {
		// Python: user_dist_over_follower.py
		
		
		FollowerNumberRanges followerNumberRanges = new FollowerNumberRanges();
		followerNumberRanges.addNewRange(0, 10, 9798);
		followerNumberRanges.addNewRange(10, 20, 1398);
		followerNumberRanges.addNewRange(20, 30, 758);
		followerNumberRanges.addNewRange(30, 40, 598);
		followerNumberRanges.addNewRange(40, 27069, 1998);
		return followerNumberRanges;
	}

	@Override
	public ForkNumberRanges getForkNumberRanges() {
		// Python: repo_dist_over_fork.py
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageCounts getLanguageCounts() {
		// Python: repo_dist_over_lang.py
		
		
		//TODO 测试用的，写好了就删掉
		LanguageCounts languageCounts = new LanguageCounts();
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
	public RepoCreateOnTimeCounts getRepoCreateOnTimeCounts() {
		// Python: repo_dist_over_create_time.py
		
		
		//TODO 测试用的，写好了就删掉
		RepoCreateOnTimeCounts repoCreateOnTimeCounts = new RepoCreateOnTimeCounts();
		repoCreateOnTimeCounts.addCreateCount("2007", 9);
		repoCreateOnTimeCounts.addCreateCount("2008", 179);
		repoCreateOnTimeCounts.addCreateCount("2009", 997);
		repoCreateOnTimeCounts.addCreateCount("2010", 5932);		
		return repoCreateOnTimeCounts;
//		return null;
	}

	@Override
	public UserCreateOnTimeCounts getUserCreateOnTimeCounts() {
		// Python: user_dist_over_create_time.py
		
		
		UserCreateOnTimeCounts userCreateOnTimeCounts = new UserCreateOnTimeCounts();
		userCreateOnTimeCounts.addCreateCount("2007", 28);
		userCreateOnTimeCounts.addCreateCount("2008", 228);
		userCreateOnTimeCounts.addCreateCount("2009", 578);
		userCreateOnTimeCounts.addCreateCount("2010", 698);
		userCreateOnTimeCounts.addCreateCount("2011", 576);
		// TODO Auto-generated method stub
		return userCreateOnTimeCounts;
	}

	@Override
	public StarCountRanges getStarCountRanges() {
		// Python: repo_dist_over_star.py 
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTypeCounts getUserTypeCounts() {
		// Python: user_dist_over_type.py
		
		//TODO 测试用的，写好了就删掉
		UserTypeCounts userTypeCounts = new UserTypeCounts();
		userTypeCounts.addCount("User", 27067);
		userTypeCounts.addCount("Organization", 950);
		return userTypeCounts;
		
//		return null
	}

}
