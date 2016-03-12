package data.service;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.manage.SpecificDataGetterDefault;

/**
 * @author xjh14
 *
 */
public abstract class SpecificDataGetter {
	
	/**
	 * 根据Repository索引查询Repository详细信息
	 * @param request 封装查询基础信息的数据索引对象RepositoryMin
	 * @return 查询的Repository对象
	 * @throws DataCorruptedException 
	 */
	public abstract Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException;

	/**
	 * 根据GitUser索引查询GitUser详细信息
	 * @param source 封装查询基础信息的数据索引GitUserMin
	 * @return 查询的GitUser对象
	 */
	public abstract GitUser getSpecificGitUser(GitUserMin source) throws NetworkException, DataCorruptedException;
	
	/**
	 * 获取实例对象。
	 */
	public static SpecificDataGetter getInstance() {
		return SpecificDataGetterDefault.getInstance();
	}
	
}
