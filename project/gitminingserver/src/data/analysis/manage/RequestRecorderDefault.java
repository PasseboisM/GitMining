package data.analysis.manage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;

import com.google.gson.Gson;

import common.enumeration.attribute.Language;
import common.exception.TargetNotFoundException;
import common.param_obj.RepositorySearchParam;
import data.analysis.service.RequestRecorder;
import data.db.service.DBAnalysisService;
import data.db.service.DBService;

/**
 * 用户的浏览记录信息Document中包含其搜索时所指定的Language与Category
 * 
 * Document内容：
 * 		user_hash----String----本处不修改
 * 		lang_pref----List<Pair<Language,Integer>>------Integer累计搜索指定次数
 * 		cate_pref----List<Pair<Category,Integer>>------Category累计搜索指定次数
 */



public class RequestRecorderDefault implements RequestRecorder {

	private DBAnalysisService db = DBService.getInstance().getAnalysisService();
	
	private Gson gson = new Gson();
	
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
		case "/GitMiningServer/repo":
			user = addRepoRequestInfo(request, user);
			break;
		case "/GitMiningServer/user":
			user = addUserRequestInfo(request, user);
			break;
		case "/GitMiningServer/stat":
			user = addStatRequestInfo(request, user);
			break;
		default:
			break;
		}
		
		db.updateIndex(key, user);
		
	}

	
	private String getUserKey(HttpServletRequest request) throws TargetNotFoundException {
		String key = request.getParameter("key");
		
		if (key==null) {
			throw new TargetNotFoundException();
		} else {
			return key;
		}
		
	}
	
	
	private Document addRepoRequestInfo(HttpServletRequest request,Document userRaw) {
		
		if ("data".equals(request.getParameter("type"))
				&&  "search".equals(request.getParameter("method"))) {
			
			RepositorySearchParam param = gson.fromJson(
					request.getParameter("param"), RepositorySearchParam.class);
			
			
			if (param==null || param.getCates()==null || param.getLangs()==null) {
				return userRaw;
			}
			
			
			
			
				
				
		}
		
		return userRaw;
	}
	
	private Document addUserRequestInfo(HttpServletRequest request,Document userRaw) {
		
		return userRaw;
	}

	private Document addStatRequestInfo(HttpServletRequest request,Document userRaw) {
		
		return userRaw;
	}
	
	public static void main(String[] args) {
		Document d = new Document("name","river");
		Document lang_pref = new Document();
		
		int i=0;
		for (Language lang:Language.values()) {
			lang_pref.append((i++)+"", new Document().append("lang", lang.toString()).append("value", 0));
		}
		d.put("lang-pref", lang_pref);
		
		System.out.println(d.toJson());
		System.out.println(new Gson().toJson(d));
		
		
	}
	
}
