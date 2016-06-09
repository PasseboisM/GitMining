package data.analysis.manage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.Document;

import com.google.gson.Gson;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.exception.TargetNotFoundException;
import common.param_obj.RepositorySearchParam;
import data.analysis.service.RequestRecorder;
import data.db.service.DBAnalysisService;
import data.db.service.DBService;
import data.db.service.obj.AnalysisRecord;
import data.db.service.obj.CatePair;
import data.db.service.obj.LanguagePair;




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
		System.out.println(user==null);
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
			
			AnalysisRecord record = gson.fromJson(
					userRaw.toJson(), AnalysisRecord.class);
			
			
			List<LanguagePair> langList = null;
			if (record.getLang_pref()==null) {
				langList = new ArrayList<>();
			} else {
				langList = new ArrayList<>(record.getLang_pref());
			}
			for (Language lang:param.getLangs()) {
				boolean matched = false;
				
				for (LanguagePair pair:langList) {
					if (lang==pair.lang) {
						pair.count++;
						matched = true;
						break;
					}
				}
				if (!matched) {
					langList.add(new LanguagePair(lang, 1));
				}
			}
			record.setLang_pref(langList);
			
			List<CatePair> cateList = null;
			if (record.getCate_pref()==null) {
				cateList = new ArrayList<>();
			} else {
				cateList = new ArrayList<>(record.getCate_pref());
			}
			for (Category cate:param.getCates()) {
				boolean matched = false;
				for (CatePair pair:cateList) {
					if (cate==pair.cate) {
						pair.count++;
						matched = true;
						break;
					}
				}
				if (!matched) {
					cateList.add(new CatePair(cate, 1));
				}
			}
			record.setCate_pref(cateList);
			
			return Document.parse(gson.toJson(record));
		}
		
		return userRaw;
	}
	
	private Document addUserRequestInfo(HttpServletRequest request,Document userRaw) {
		
		return userRaw;
	}

	private Document addStatRequestInfo(HttpServletRequest request,Document userRaw) {
		
		return userRaw;
	}
}
