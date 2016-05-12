package logic.service;

import common.exception.TargetNotFoundException;
import common.service.GitUser;

/**
 * 用于用户登录功能
 * @author xjh14
 * Ver: 1.0
 * Created at: 2016年5月9日
 */
public interface LogInHelper {
	
	/**
	 * 允许用户进行登陆，成功登陆后会指定此用户为当前系统使用者
	 * @param login 登陆名或登陆邮箱
	 * @param password 登陆密码
	 * @return GitUser 登陆成功返回登陆用户的数据
	 * @throws TargetNotFoundException 登录失败
	 */
	public GitUser tryLogIn(String login, String password)
			throws TargetNotFoundException;
	
	/**
	 * 当前用户登出；若没有用户处于登陆状态，则调用接口方法无效
	 */
	public void logOut();
}
