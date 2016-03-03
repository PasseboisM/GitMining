package common.enumeration;

/**
 * 
 * @author River
 * 搜索、排序时使用的枚举类型，表示项目所使用的语言
 * 可用于对Repository进行分类
 * TODO 更多语言类型
 */
public enum Language {
	ALL("all"),
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
}
