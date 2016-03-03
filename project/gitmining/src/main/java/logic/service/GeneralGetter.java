package logic.service;

import java.util.List;

import common.enumeration.RepoSortStadard;
import common.service.Repository;

/**
 * 
 * @author River
 * 笼统获取各种数据对象的服务，提供排序功能
 * TODO 更多数据种类：GitUser...
 */
public interface GeneralGetter {

	/**
	 * 获取一页Repository详细信息，页与页之间不重复；
	 * 按给定的的排序方式给出
	 * @param page 所请求的页数（从1开始）
	 * @param numPerPage 请求返回的每页数量（数据不足时返回的对象数目<numPerPage）
	 * @param sortStandard 请求的排序方式
	 * @return 含有所请求的Repository的数据对象的List，其大小<=page
	 */
	public List<Repository> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard);
	
	/**
	 * 获取系统内Repository总共数目
	 * @return Repository总数目
	 */
	public int getNumOfRepositories();
}
