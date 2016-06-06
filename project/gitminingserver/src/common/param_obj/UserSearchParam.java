package common.param_obj;

import common.enumeration.sort_standard.UserSortStandard;

/**
 * 
 * @author xjh14
 * 搜索GitUser时使用的参数封装类，用于提高接口灵活性、简洁度。
 * 当前包含：搜索关键字keywords, 排序方式sortStandard
 */
public class UserSearchParam {

	private String[] keywords;

	private UserSortStandard sortStandard;
	
	public UserSearchParam(String[] keys, UserSortStandard sortStandard) {
		super();
		this.keywords = keys;
		this.sortStandard = sortStandard;
	}

	public UserSortStandard getSortStandard() {
		return sortStandard;
	}
	
	public String getKeywordInLine() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<keywords.length;i++) {
			sb.append(keywords[i]);
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public String[] getKeywords() {
		return keywords;
	}
	
}
