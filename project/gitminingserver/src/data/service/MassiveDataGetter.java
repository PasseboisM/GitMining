package data.service;

import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import data.manage.MassiveDataGetterNetwork;

/**
 * @author xjh14
 * 提供获取所有数据对象索引服务
 */
public abstract class MassiveDataGetter {

	/**
	 * 获取一页Repository详细信息，页与页之间不重复；
	 * 按给定的的排序方式给出
	 * @param page 所请求的页数（从1开始）
	 * @param numPerPage 请求返回的每页数量（数据不足时返回的对象数目<numPerPage）
	 * @param sortStandard 请求的排序方式
	 * @return 含有所请求的Repository的数据对象JSON表达形式的List，其大小<=page
	 * @throws IndexOutOfBoundsException 请求的(page-1)*numPerPage大于总共个数时出现
	 */
	public abstract List<String> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) throws IndexOutOfBoundsException;
	
	/**
	 * 获取系统内Repository总共数目
	 * @return Repository总数目
	 */
	public abstract int getNumOfRepositories();
	
	/**
	 * 获取系统内GitUser总数目
	 * @return 系统内GitUser数据数目
	 */
	public abstract int getNumOfUsers();
	
	/**
	 * 获取一页GitUser详细信息，页与页之间不重复
	 * （当前需求分析对于User没有排序要求，但将来很可能会有，如按follower数目，故加上排序参数）
	 * @param page 所请求的页数（从1开始）
	 * @param numPerPage 请求返回的每页数量（数据不足时返回的对象数目<numPerPage）
	 * @param sortStandard 排序方式
	 * @return 包含所请求GitUser信息的List对象
	 * @throws IndexOutOfBoundsException 请求的(page-1)*numPerPage大于总共个数时出现
	 */
	public abstract List<String> getUsers(int page, int numPerPage,UserSortSandard sortStandard) throws IndexOutOfBoundsException;
	
	/**
	 * 提供根据参数中关键词属性枚举与关键词字符串获取Repository的搜索结果
	 * @param params 搜索参数，详见RepositorySearchParam
	 * @return 存放Repository详细信息对象的列表
	 */
	public abstract List<String> searchRepository(RepositorySearchParam params);
	
	/**
	 * 提供根据参数中属性获取GitUser的搜索结果
	 * @param params 搜索参数，详见UserSearchParam
	 * @return 存放GitUser信息对象的列表
	 */
	public abstract List<String> searchUser(UserSearchParam params);
	
	/**
	 * 获取实例对象。
	 */
	public static MassiveDataGetter getInstance() {
		return MassiveDataGetterNetwork.getInstance();
	}
	
}
