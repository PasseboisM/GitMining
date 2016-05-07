package common.param_obj;

import org.kohsuke.github.GHIssueSearchBuilder.Sort;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import common.enumeration.sort_standard.UserSortSandard;

/**
 * 
 * @author xjh14
 * 搜索GitUser时使用的参数封装类，用于提高接口灵活性、简洁度。
 * 当前包含：User的登录名LoginName
 */
public class UserSearchParam {

	private String loginName;

	private UserSortSandard sortStandard;
	
	public UserSearchParam(String loginName, UserSortSandard sortStandard) {
		super();
		this.loginName = loginName;
		this.sortStandard = sortStandard;
	}

	public UserSortSandard getSortStandard() {
		return sortStandard;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
}
