package data.service;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import common.util.ObjChannel;
import data.manage.MassiveDataGetterDefault;

/**
 * @author xjh14
 * 提供获取所有数据对象索引服务
 */
public abstract class MassiveDataGetter {

	public abstract int getRepoNumber();
	
	/**
	 * 获取提供Repository数据索引的通道
	 * @return 包含所有Repository数据索引的Channel服务
	 */
	public abstract ObjChannel<RepositoryMin> getRepoMinInfo();
	
	/**
	 * 获取提供GitUser数据索引的通道
	 * @return 包含所有GitUser数据索引的Channel服务
	 */
	public abstract ObjChannel<GitUserMin> getUserMinInfo();
	
	/**
	 * 获取实例对象。
	 */
	public static MassiveDataGetter getInstance() {
		return MassiveDataGetterDefault.getInstance();
	}
}
