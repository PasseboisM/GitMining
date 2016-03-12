package logic.service;

import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;

/**
 * @author xjh14
 * 按照枚举项目+关键词的方式进行搜索，
 * 内容包括项目Repository、用户GitUser
 */
public interface SearchService {
	
	/**
	 * 提供根据参数中关键词属性枚举与关键词字符串获取Repository的搜索结果
	 * @param params 搜索参数，详见RepositorySearchParam
	 * @return 存放Repository详细信息对象的列表
	 * @throws DataCorruptedException 
	 * @throws NetworkException 
	 */
	public List<Repository> searchRepository(RepositorySearchParam params) throws NetworkException, DataCorruptedException;
	
	/**
	 * 提供根据参数中属性获取GitUser的搜索结果
	 * @param params 搜索参数，详见UserSearchParam
	 * @return 存放GitUser信息对象的列表
	 * @throws DataCorruptedException 
	 * @throws NetworkException 
	 */
	public List<GitUser> searchUser(UserSearchParam params) throws NetworkException, DataCorruptedException;
}
