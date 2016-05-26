package data.service;

import common.exception.TargetNotFoundException;
import data.manage.SpecificDataGetterNetwork;

/**
 * @author xjh14
 *
 */
public abstract class SpecificDataGetter {
	
	/**
	 * 根据Repository全名查询Repository详细信息
	 * @param fullName Repository的全名(owner_name/repo_name)
	 * @return 查询的Repository对象的JSON序列化表示
	 * @throws TargetNotFoundException 未能找到fullName标识的Repository
	 */
	public abstract String getSpecificRepo(String fullName) throws TargetNotFoundException;

	/**
	 * 根据GitUser的login查询GitUser详细信息
	 * @param login GitUser的login，可为注册名可为邮箱地址
	 * @return 查询的GitUser对象的JSON序列化表示
	 * @throws TargetNotFoundException 未能找到login标识的GitUser
	 */
	public abstract String getSpecificGitUser(String login) throws TargetNotFoundException;
	
	/**
	 * 获取实例对象。
	 */
	public static SpecificDataGetter getInstance() {
		return SpecificDataGetterNetwork.getInstance();
	}
	
}
