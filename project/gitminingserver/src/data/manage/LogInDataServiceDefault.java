package data.manage;

import org.bson.Document;

import com.google.gson.Gson;

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
	
	public static void main(String[] args) {
		Document doc = new Document("state", true).append("key", "123456");
		System.out.println(new Gson().toJson(doc));
	}
	
}
