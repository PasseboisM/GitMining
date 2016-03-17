package common.enumeration.attribute;

/**
 * 
 * @author xjh14
 * 搜索、排序时使用的枚举类型，表示项目所使用的语言
 * 可用于对Repository进行分类
 * TODO 更多语言类型
 */
public enum Language {
	ALL("all"),
	JAVA("Java"),
	RUBY("Ruby"),
	PYTHON("Python"),
	C("C"),
	JAVA_SCRIPT("JavaScript"),
	PERL("Perl"),
	PHP("PHP"),
	C_PLUS_PLUS("C++"),
	HTML("HTML"),
	SHELL("Shell"),
	OBJECTIVE_C("Objective-C"),
	VIML("VimL"),
	C_SHARP("C#"),
	EMACS_LISP("Emacs Lisp"),
	ERLANG("Erlang"),
	LUA("Lua"),
	CLOJURE("Clojure"),
	CSS("CSS"),
	HASKELL("Haskell"),
	SCALA("Scala"),
	COMMON_LISP("Common Lisp"),
	R("R"),
	OTHERS("Others");
	
	
	
	
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
		return OTHERS;
	}
}
