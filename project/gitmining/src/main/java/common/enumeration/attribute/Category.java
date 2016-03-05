package common.enumeration.attribute;

/**
 * 
 * @author xjh14
 * 搜索、排序时使用的枚举类型，表示项目内容关键词
 * 可用于对Repository进行分类
 * TODO 更多内容关键字
 */
public enum Category {
	ALL("all"),
	ACTIVE_RECORD("ActiveRecord"),
	API("API");
	
	
	String name;
	Category(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
