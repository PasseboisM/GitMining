package network.service;

import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;

/**
 * @author xjh14
 * 网络层提供的获取大量数据索引对象或数据对象的接口。
 * 其中索引对象可用于系统启动初始化；
 * 数据对象用于更新本地数据。
 */
public interface MassiveDataSource {

	/**
	 * 获取Repository数据full name列表
	 * @return 包含所有Repository数据索引的List
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public ObjChannel<String> getRepoNames() throws NetworkException;
	
	/**
	 * 获取提供Repository数据索引的通道
	 * @return 传送包含所有Repository数据索引的数据通道
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public ObjChannel<RepositoryMin> getRepoMinInfo() throws NetworkException;
	
	/**
	 * 获取提供Repository数据索引的通道。
	 * Warning: 由于数据量大，应仅用于更新全部数据时使用。
	 * @return 包含所有Repository详细数据的List
	 * @throws NetworkException 发生网络异常，无法获取信息
	 */
	public ObjChannel<Repository> getRepoInfo() throws NetworkException;
	
	/**
	 * 获取全部GitUser数据索引的通道。
	 * @return 包含所有GitUser数据索引的List
	 * @throws NetworkException 
	 */
	public ObjChannel<GitUserMin> getUserMinInfo() throws NetworkException;
	
	/**
	 * 获取提供GitUser数据的通道。
	 * Warning: 由于数据量大，应仅用于更新全部数据时使用。
	 * @return 传送包含所有GitUser数据索引的数据通道
	 * @throws NetworkException 
	 */
	public ObjChannel<GitUser> getUserInfo() throws NetworkException;
}
