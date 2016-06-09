package data.db.service.obj;

import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;

/**
 * 用户的浏览记录信息Document中包含其搜索时所指定的Language与Category
 * 
 * Document内容：
 * 		user_hash----String----本处不修改
 * 		lang_pref----List<Pair<Language lang,Integer count>>------Integer累计搜索指定次数
 * 		cate_pref----List<Pair<Category cate,Integer count>>------Category累计搜索指定次数
 */



public class AnalysisRecord {

	public String user_hash;
	public List<LanguagePair> lang_pref;
	public List<CatePair> cate_pref;
	
	public List<LanguagePair> getLang_pref() {
		return lang_pref;
	}
	public void setLang_pref(List<LanguagePair> lang_pref) {
		this.lang_pref = lang_pref;
	}
	public List<CatePair> getCate_pref() {
		return cate_pref;
	}
	public void setCate_pref(List<CatePair> cate_pref) {
		this.cate_pref = cate_pref;
	}
	
}


