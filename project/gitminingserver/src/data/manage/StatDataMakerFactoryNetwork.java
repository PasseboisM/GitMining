package data.manage;

import data.service.StatDataMakerFactory;
import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;

public class StatDataMakerFactoryNetwork implements StatDataMakerFactory {
	
	@Override
	public GeneralStatGetter getGeneralStatGetter() {
		//TODO
		return null;
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



}
