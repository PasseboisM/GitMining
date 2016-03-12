package common.service;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.util.Checkable;

/**
 * 
 * @author xjh14
 * 可常驻内存的Repository数据索引
 */
public interface RepositoryMin extends Checkable{

	public int getId();

	public String getName();

	public String getFull_name();

	public boolean isPrivate();

	public int getStargazers_count();

	public int getForks_count();
	
	public String getLanguage();

	public Language getMainLanguage();
	
	public Category[] getCategories();
}