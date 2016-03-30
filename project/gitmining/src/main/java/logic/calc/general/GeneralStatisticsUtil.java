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
		
		//TODO 测试用的，写好了就删掉
		UserTypeCounts userTypeCounts = new UserTypeCounts();
		userTypeCounts.addCount("User", 27067);
		userTypeCounts.addCount("Organization", 950);
		return userTypeCounts;
		
//		return null
	}

}
