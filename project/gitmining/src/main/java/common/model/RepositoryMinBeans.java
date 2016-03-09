package common.model;

import com.google.gson.annotations.SerializedName;

import common.service.RepositoryMin;

/**
 * @author xjh14
 * 包含排序、搜索使用的最简信息的RepositoryMin的Beans，用于降低内存消耗、提升响应时间
 * 对本类实际不包含的不包含的数据进行query将会抛出异常
 * TODO 尚未决定其中所包含的具体信息条目
 */
public class RepositoryMinBeans implements RepositoryMin {

	private int id;
	private String full_name;
	private String name;
	@SerializedName("private") private boolean isPrivate;
	private int stargazers_count;
	private int forks_count;
	private String language;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFull_name() {
		return full_name;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public int getForks_count() {
		return forks_count;
	}

	@Override
	public String getLanguage() {
		return language;
	}

}
