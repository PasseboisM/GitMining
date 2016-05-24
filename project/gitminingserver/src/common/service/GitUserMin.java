package common.service;

import common.util.Checkable;

/**
 * 
 * @author xjh14
 * GitUser数据索引类，用于搜索，排序等
 * 
 */
public interface GitUserMin extends Checkable{

	public String getLogin();
	public int getId();
	public String getName();
	public int getFollowers();
}
