package data.service;

import common.service.Repository;
import common.util.ObjChannel;

/**
 * @author River
 * 提供获取所有数据对象索引服务
 */
public interface MassiveDataGetter {

	/**
	 * 获取提供Repository数据索引的通道
	 * @return 包含所有Repository数据索引的List
	 */
	public ObjChannel<Repository> getRepoMinInfo();
	
}
