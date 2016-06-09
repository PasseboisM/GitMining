package data.manage;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import common.exception.NetworkException;
import common.exception.TargetNotFoundException;
import common.model.beans.GitUserBeans;
import common.service.GitUser;
import data.service.DataServiceFactory;
import data.service.LogInDataService;
import data.service.SpecificDataGetter;
import network.service.GHDataSource;
import network.service.NetworkServiceFactory;

public class LogInDataServiceDefault implements LogInDataService {

	private static LogInDataServiceDefault instance = new LogInDataServiceDefault();
	
	private GHDataSource checker = NetworkServiceFactory.getInstance().getGHDataSource();
	private SpecificDataGetter specific = 
			DataServiceFactory.getInstance().getSpecificDataGetter();
	
	private final String FAILURE_LOG_IN;
	
	private Gson gson = new Gson();
	
	private LogInDataServiceDefault() {
		FAILURE_LOG_IN = new Document("state",false)
				.append("key", "")
				.toJson();
	}
	
	@Override
	public String tryLogIn(String login, String password) {
		// TODO 现在暂时不提供实际历史记录功能
		
		boolean state = false;
		
		try {
			state = checker.isCredentialValid(login, password);	
		} catch (NetworkException e) {
			return FAILURE_LOG_IN;
		}
		
		if (state && !login.contains("@")) {
			try {
				GitUser user =
						gson.fromJson(specific.getSpecificGitUser(login), GitUserBeans.class);
				login = user.getEmail();
			} catch (JsonSyntaxException | TargetNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		Document doc = new Document("state",state);
				
		doc.append("key", state?""+login.hashCode():"");
		
		return doc.toJson();
	}

	public static LogInDataServiceDefault getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Document doc = new Document("state", true).append("key", "123456");
		System.out.println(new Gson().toJson(doc));
	}
	
}
