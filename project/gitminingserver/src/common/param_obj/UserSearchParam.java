package common.param_obj;

import common.enumeration.sort_standard.UserSortStandard;

/**
 * 
 * @author xjh14
 * 搜索GitUser时使用的参数封装类，用于提高接口灵活性、简洁度。
 * 当前包含：User的登录名LoginName
 */
public class UserSearchParam {

	private String loginName;

	private UserSortStandard sortStandard;
	
	public UserSearchParam(String loginName, UserSortStandard sortStandard) {
		super();
		this.loginName = loginName;
		this.sortStandard = sortStandard;
	}

	public UserSortStandard getSortStandard() {
		return sortStandard;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
}
