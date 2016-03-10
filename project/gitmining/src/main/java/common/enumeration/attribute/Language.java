package common.enumeration.attribute;

/**
 * 
 * @author xjh14
 * 搜索、排序时使用的枚举类型，表示项目所使用的语言
 * 可用于对Repository进行分类
 * TODO 更多语言类型
 */
public enum Language {
	ALL("All"),
	UNKNOWN("Unknown"),
	RUBY("Ruby"),
	JAVA("Java"),
	C("C");
	
	
	
	String name;
	Language(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Language getLanguage(String name) {
		for(Language lan:Language.values()) {
			if(lan.getName().equals(name)) {
				return lan;
			}
		}
		return UNKNOWN;
	}
}
