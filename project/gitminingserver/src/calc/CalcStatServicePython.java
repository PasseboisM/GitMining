package calc;

import calc.python.RepoStatCalcPython;
import calc.python.UserStatCalcPython;
import calc.service.CalcStatService;
import calc.service.RepoStatService;
import calc.service.UserStatService;

public class CalcStatServicePython extends CalcStatService {

	private static CalcStatServicePython instance = new CalcStatServicePython();
	
	private RepoStatService repoService = new RepoStatCalcPython();
	private UserStatService userService = new UserStatCalcPython();
	
	private CalcStatServicePython() {}
	
	@Override
	public RepoStatService getRepoStatService() {
		return repoService;
	}

	@Override
	public UserStatService getUserStatService() {
		return userService;
	}
	
	public static CalcStatServicePython getInstance() {
		return instance;
	}

}
