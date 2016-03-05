package common.service;

/**
 * 
 * @author xjh14
 * 可常驻内存的Repository数据索引
 */
public interface RepositoryMin {

	public int getId();

	public String getName();

	public String getFull_name();

	public boolean isPrivate();

	public int getStargazers_count();

	public int getForks_count();

}