package data.manage;

import data.manage.statistic.GeneralStatGetterNetwork;
import data.manage.statistic.RepositoryStatGetterNetwork;
import data.manage.statistic.UserStatGetterNetwork;
import data.service.StatDataMakerFactory;
import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;

public class StatDataMakerFactoryNetwork implements StatDataMakerFactory {
	
	private static StatDataMakerFactoryNetwork instance = new StatDataMakerFactoryNetwork();
	
	private GeneralStatGetter general = new GeneralStatGetterNetwork();
	private UserStatGetter userStat = new UserStatGetterNetwork();
	private RepoStatGetter repoStat = new RepositoryStatGetterNetwork();
	
	@Override
	public GeneralStatGetter getGeneralStatGetter() {
		return general;
	}

	@Override
	public UserStatGetter getUserStatGetter() {
		return userStat;
	}

	@Override
	public RepoStatGetter getRepoStatGetter() {
		return repoStat;
	}

	public static StatDataMakerFactoryNetwork getInstance() {
		return instance;
	}

}
