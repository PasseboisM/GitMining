package data.service;

import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;

public interface StatDataMakerFactory {

	public GeneralStatGetter getGeneralStatGetter();
	
	public UserStatGetter getUserStatGetter();
	
	public RepoStatGetter getRepoStatGetter();
	
}
