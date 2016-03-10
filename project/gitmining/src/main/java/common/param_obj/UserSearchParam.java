package common.param_obj;

import common.service.GitUserMin;

/**
 * 
 * @author xjh14
 * 搜索GitUser时使用的参数封装类，用于提高接口灵活性、简洁度。
 * 当前包含：User的登录名LoginName
 */
public class UserSearchParam {

	private String loginName;

	public UserSearchParam(String loginName) {
		super();
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}
	
	public boolean matches(GitUserMin minInfo) {
		//TODO Create a checker.
		return false;
	}
}
