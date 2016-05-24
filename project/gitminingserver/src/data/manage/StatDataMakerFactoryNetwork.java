package data.manage;

import data.manage.statistic.GeneralStatGetterNetwork;
import data.service.StatDataMakerFactory;
import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;

public class StatDataMakerFactoryNetwork implements StatDataMakerFactory {
	
	private static StatDataMakerFactoryNetwork instance = new StatDataMakerFactoryNetwork();
	
	private GeneralStatGetter general = new GeneralStatGetterNetwork();
	
	@Override
	public GeneralStatGetter getGeneralStatGetter() {
		return general;
	}

	@Override
	public UserStatGetter getUserStatGetter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepoStatGetter getRepoStatGetter() {
		// TODO Auto-generated method stub
		return null;
	}

	public static StatDataMakerFactoryNetwork getInstance() {
		return instance;
	}

}
