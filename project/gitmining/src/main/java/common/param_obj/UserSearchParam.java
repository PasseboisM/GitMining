package common.param_obj;

import common.enumeration.sort_standard.UserSortSandard;
import common.service.GitUserMin;

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
	
	//TODO 搬移搜索策略
	public int matches(GitUserMin minInfo) {
		if(nameMatch(minInfo.getLogin(),loginName)||nameMatch(minInfo.getName(),loginName)) {
			return 1;
		}
		return 0;
	}
	
	private boolean nameMatch(String original,String searched) {
		return original.toLowerCase().contains(searched.toLowerCase());
	}
}
