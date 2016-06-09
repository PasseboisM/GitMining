package data.analysis.manage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;

import common.exception.TargetNotFoundException;
import data.analysis.service.RequestRecorder;
import data.db.service.DBAnalysisService;
import data.db.service.DBService;

public class RequestRecorderDefault implements RequestRecorder {

	private DBAnalysisService db = DBService.getInstance().getAnalysisService();
	
	@Override
	public void record(HttpServletRequest request) {
		String key = null;
		
		try {
			key = getUserKey(request);
		} catch (TargetNotFoundException e) {
			return;
		}
		
		System.out.println("Got user "+key+
				" sending request "+request.getRequestURI()+'?'+request.getQueryString());
		
		db.ensureUser(key);
		
		Document user = db.getUserIndex(key);
		
		System.out.println("User's index:"+user.toJson());
		
		switch (request.getRequestURI()) {
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		default:
			break;
		}
	}

	
	private String getUserKey(HttpServletRequest request) throws TargetNotFoundException {
		Cookie[] cookies = request.getCookies();
		for (Cookie c:cookies) {
			if (c.getName().equals("key")) {
				return c.getValue();
			}
		}
		throw new TargetNotFoundException();
	}
	
}
