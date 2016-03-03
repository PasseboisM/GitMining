package network.service;

import common.service.Repository;
import common.service.RepositoryMin;
import common.util.ObjChannel;

public interface MassiveDataSource {

	/**
	 * 获取Repository数据full name列表
	 * @return 包含所有Repository数据索引的List
	 */
	public ObjChannel<String> getRepoNames();
	
	/**
	 * 获取提供Repository数据索引的通道
	 * @return 包含所有Repository数据索引的List
	 */
	public ObjChannel<RepositoryMin> getRepoMinInfo();
	
	
	/**
	 * 获取提供Repository数据索引的通道
	 * @return 包含所有Repository详细数据的List
	 */
	public ObjChannel<Repository> getRepoInfo();
}
