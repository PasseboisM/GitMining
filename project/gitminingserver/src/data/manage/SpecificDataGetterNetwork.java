package data.manage;

import common.exception.TargetNotFoundException;
import data.service.SpecificDataGetter;

public class SpecificDataGetterNetwork extends SpecificDataGetter {
	
	private static SpecificDataGetterNetwork instance = new SpecificDataGetterNetwork();

	@Override
	public String getSpecificRepo(String fullName) throws TargetNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSpecificGitUser(String login) throws TargetNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static SpecificDataGetterNetwork getInstance() {
		return instance;
	}
	
}
