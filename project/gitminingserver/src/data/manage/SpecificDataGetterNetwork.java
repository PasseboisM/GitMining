package data.manage;

import com.google.gson.Gson;

import common.exception.NetworkException;
import common.exception.TargetNotFoundException;
import data.db.service.DBRepoService;
import data.db.service.DBService;
import data.db.service.DBUserService;
import data.service.SpecificDataGetter;
import network.service.GHDataSource;
import network.service.NetworkServiceFactory;
import network.service.SpecificDataSource;

public class SpecificDataGetterNetwork extends SpecificDataGetter {
	
	private static SpecificDataGetterNetwork instance = new SpecificDataGetterNetwork();
	
	private Gson gson = new Gson();
	
	private DBRepoService dbRepoService = DBService.getInstance().getRepoService();
	private DBUserService dbUserService = DBService.getInstance().getUserService();
	private SpecificDataSource networkSpecific = NetworkServiceFactory.getInstance().getSpecificDataSource();
	
	private SpecificDataGetterNetwork(){}
	
	@Override
	public String getSpecificRepo(String fullName) throws TargetNotFoundException {

		String result = null;
		
		try {
			result = dbRepoService.getRepository(fullName);
			return result;
		} catch (TargetNotFoundException e) {
			try {
				result = gson.toJson(networkSpecific.getSpecificRepo(fullName));
				return result;
			} catch (NetworkException e1) {
				throw e;
			}
		}
	}

	@Override
	public String getSpecificGitUser(String login) throws TargetNotFoundException {
		String result = null;
		
		try {
			result = dbUserService.getUser(login);
			return result;
		} catch (TargetNotFoundException e) {
			try {
				result = gson.toJson(networkSpecific.getSpecificUser(login));
				return result;
			} catch (NetworkException e1) {
				throw e;
			}
		}
	}
	
	public static SpecificDataGetterNetwork getInstance() {
		return instance;
	}
	
}
