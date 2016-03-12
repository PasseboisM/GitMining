package network.service;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

/**
 * @author xjh14
 * 网络层提供的根据数据索引获取具体数据对象的接口。
 */
public interface SpecificDataSource {

	/**
	 * @deprecated
	 * 根据Repository索引查询Repository详细信息。
	 * 讲道理应该用参数为String的重载方法比较好。
	 * @param request 封装查询基础信息的数据索引对象
	 * @return 查询的Repository数据对象
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException;
	
	/**
	 * 根据FullName查询Repository详细信息
	 * @param fullName Repo的全名
	 * @return 查询的Repository数据对象
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public Repository getSpecificRepo(String fullName) throws NetworkException, DataCorruptedException;
	
	/**
	 * @deprecated
	 * 根据GitUser索引查询其详细信息
	 * @param source GitUser数据索引对象
	 * @return 查询的GitUser数据对象
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public GitUser getSpecificUser(GitUserMin source) throws NetworkException;
	
	/**
	 * 根据FullName查询GitUser详细信息
	 * @param login Git用户的登录名
	 * @return 查询的GitUser数据对象
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public GitUser getSpecificUser(String login) throws NetworkException;
}
