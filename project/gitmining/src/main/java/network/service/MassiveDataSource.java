package network.service;

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
	 */
	public ObjChannel<String> getRepoNames();
	
	/**
	 * 获取提供Repository数据索引的通道
	 * @return 传送包含所有Repository数据索引的数据通道
	 */
	public ObjChannel<RepositoryMin> getRepoMinInfo();
	
	/**
	 * 获取提供Repository数据索引的通道。
	 * Warning: 由于数据量大，应仅用于更新全部数据时使用。
	 * @return 包含所有Repository详细数据的List
	 */
	public ObjChannel<Repository> getRepoInfo();
	
	/**
	 * 获取全部GitUser数据索引的通道。
	 * @return 包含所有GitUser数据索引的List
	 */
	public ObjChannel<GitUserMin> getUserMinInfo();
	
	/**
	 * 获取提供GitUser数据的通道。
	 * Warning: 由于数据量大，应仅用于更新全部数据时使用。
	 * @return 传送包含所有GitUser数据索引的数据通道
	 */
	public ObjChannel<GitUser> getUserInfo();
}
