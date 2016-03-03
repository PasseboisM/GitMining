package logic.service;

import java.util.List;

import common.enumeration.Category;
import common.enumeration.Language;
import common.service.Repository;

/**
 * @author River
 * 按照枚举项目+关键词的方式进行搜索，
 * 内容包括项目Repository、用户GitUser
 * TODO Iter1使用本模块，注意尽早编写（暂时只提供项目搜索接口，不提供用户搜索接口）
 */
public interface SearchService {
	
	/**
	 * 提供根据参数中关键词属性枚举与关键词字符串获取的搜索结果
	 * @param lans 项目语言限制
	 * @param cats 项目内容限制
	 * @param keywords 项目描述关键词限制
	 * @return 存放项目详细信息对象的列表
	 */
	public List<Repository> searchRepository(Language[] lans,Category[] cats,String[] keywords);
	
	
}
