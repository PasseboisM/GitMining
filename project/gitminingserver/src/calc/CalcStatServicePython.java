package calc;

import calc.service.CalcStatService;
import calc.service.RepoStatService;
import calc.service.UserStatService;

public class CalcStatServicePython extends CalcStatService {

	private static CalcStatServicePython instance = new CalcStatServicePython();
	
	private CalcStatServicePython() {}
	
	@Override
	public RepoStatService getRepoStatService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserStatService getUserStatService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static CalcStatServicePython getInstance() {
		return instance;
	}

}
