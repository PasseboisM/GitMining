package common.model;

import common.service.RepositoryMin;

/**
 * @author River
 * 包含排序、搜索使用的最简信息的RepositoryMin的Beans，用于降低内存消耗、提升响应时间
 * 对本类实际不包含的不包含的数据进行query将会抛出异常
 * TODO 尚未决定其中所包含的具体信息条目
 */
public class RepositoryMinBeans implements RepositoryMin {

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFull_name() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPrivate() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getStars() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getForkNum() {
		// TODO Auto-generated method stub
		return 0;
	}

}
