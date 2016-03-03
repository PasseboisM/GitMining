package logic.service;

import java.util.List;

import common.service.Repository;

/**
 * 
 * @author River
 * 笼统获取各种数据对象的服务，可能只用于迭代一
 * 
 */
public interface GeneralGetter {

	/**
	 * 获取一页Repository详细信息，页与页之间不重复；
	 * 不保证按一定的排序给出
	 * @param page 所请求的页数（从1开始）
	 * @param numPerPage 请求返回的每页数量（数据不足时返回的对象数目<numPerPage）
	 * @return 含有所请求的Repository的数据对象的List，其大小<=page
	 */
	public List<Repository> getRepositories(int page, int numPerPage);
	
	/**
	 * 获取系统内Repository总共数目
	 * @return Repository总数目
	 */
	public int getNumOfRepositories();
}
