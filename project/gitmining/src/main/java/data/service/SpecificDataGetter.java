package data.service;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

/**
 * @author xjh14
 *
 */
public interface SpecificDataGetter {
	
	/**
	 * 根据Repository索引查询Repository详细信息
	 * @param request 封装查询基础信息的数据索引对象RepositoryMin
	 * @return 查询的Repository对象
	 */
	public Repository getSpecificRepo(RepositoryMin source);

	/**
	 * 根据GitUser索引查询GitUser详细信息
	 * @param source 封装查询基础信息的数据索引GitUserMin
	 * @return 查询的GitUser对象
	 */
	public GitUser getSpecificGitUser(GitUserMin source);
	
}