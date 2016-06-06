package data.service.stat;

import common.exception.TargetNotFoundException;

/**
 * 本接口用于获取用于构造各种RepoRank对象的JSON字符串
 * @author River
 */
public interface RepoStatGetter {


	public String getRepoRanks(String fullName) throws TargetNotFoundException;
}
