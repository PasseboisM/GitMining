package network.service;

import java.util.List;

import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;

public interface GHDataSource {
	/**
	 * 通过项目搜索参数搜索相关项目
	 * @param repositorySearchParam 项目搜索参数
	 * @return 符合条件的项目列表
	 * @throws NetworkException 网络异常
	 */
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam) throws NetworkException;
	/**
	 * 通过用户搜索条件搜索相关用户
	 * @param userSearchParam 用户搜索参数
	 * @return 符合条件的用户列表
	 * @throws NetworkException 网络异常
	 */
	public List<GitUser> searchUser(UserSearchParam userSearchParam) throws NetworkException;
	/**
	 * 检查用户登录名及密码是否合法
	 * @param login 登录名
	 * @param password 密码
	 * @return 合法性
	 * @throws NetworkException 网络异常
	 */
	public boolean isCredentialValid(String login,String password) throws NetworkException;
}
