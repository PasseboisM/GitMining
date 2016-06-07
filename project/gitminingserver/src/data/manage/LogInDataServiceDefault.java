package data.manage;

import data.service.LogInDataService;

public class LogInDataServiceDefault implements LogInDataService {

	private static LogInDataServiceDefault instance = new LogInDataServiceDefault();
	
	private LogInDataServiceDefault() {}
	
	@Override
	public String tryLogIn(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public static LogInDataServiceDefault getInstance() {
		return instance;
	}
	
}
