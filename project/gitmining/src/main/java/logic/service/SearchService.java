package logic.service;

import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;

/**
 * @author xjh14
 * 按照枚举项目+关键词的方式进行搜索，
 * 内容包括项目Repository、用户GitUser
 * TODO Iter1使用本模块，注意尽早编写（暂时只提供项目搜索接口，不提供用户搜索接口）
 */
public interface SearchService {
	
	/**
	 * 提供根据参数中关键词属性枚举与关键词字符串获取Repository的搜索结果
	 * @param params 搜索参数，详见RepositorySearchParam
	 * @return 存放Repository详细信息对象的列表
	 */
	public List<Repository> searchRepository(RepositorySearchParam params);
	
	/**
	 * 提供根据参数中属性获取GitUser的搜索结果
	 * @param params 搜索参数，详见UserSearchParam
	 * @return 存放GitUser信息对象的列表
	 */
	public List<GitUser> searchUser(UserSearchParam params);
}
