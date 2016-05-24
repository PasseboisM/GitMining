package common.enumeration.attribute;

import common.service.RepositoryMin;

/**
 * 
 * @author xjh14
 * 搜索、排序时使用的枚举类型，表示项目内容关键词
 * 可用于对Repository进行分类
 */
public enum Category {
	ALL("all"),
	ACTIVE_RECORD("ActiveRecord"),
	API("API"),
	APP("app"),
	CMS("CMS"),
	DJANGO("Django"),
	EMACS("Emacs"),
	FRAMEWORK("framework"),
	INTERFACE("interface"),
	IRC("IRC"),
	JSON("JSON"),
	LIBRARY("library"),
	LINUX("Linux"),
	MAC("Mac"),
	MANAGEMENT("management"),
	OS("OS"),
	PLUGIN("plugin"),
	RAILS("Rails"),
	REDIS("Redis"),
	SERVER("server"),
	SOURCE("source"),
	TEMPLATE("template"),
	TEXT_MATE("TextMate"),
	TOOL("tool"),
	WEB("Web"),
	WEBSITE("website"),
	OTHERS("Others");
	
	
	String name;
	Category(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public static Category[] getCategories(RepositoryMin minInfo) {
		//TODO Analyze categories
		return new Category[0];
	}
}
