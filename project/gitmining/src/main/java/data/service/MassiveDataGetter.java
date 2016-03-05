package data.service;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;

/**
 * @author xjh14
 * 提供获取所有数据对象索引服务
 */
public interface MassiveDataGetter {

	/**
	 * 获取提供Repository数据索引的通道
	 * @return 包含所有Repository数据索引的Channel服务
	 */
	public ObjChannel<RepositoryMin> getRepoMinInfo();
	
	/**
	 * 获取提供GitUser数据索引的通道
	 * @return 包含所有GitUser数据索引的Channel服务
	 */
	public ObjChannel<GitUserMin> getUserMinInfo();
}
