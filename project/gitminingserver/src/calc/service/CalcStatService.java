package calc.service;

import calc.CalcStatServicePython;

public abstract class CalcStatService {

	private static CalcStatService instance = CalcStatServicePython.getInstance();
	
	public abstract RepoStatService getRepoStatService();
	
	public abstract UserStatService getUserStatService();
	
	public static CalcStatService getInstance() {
		return instance;
	}
}
