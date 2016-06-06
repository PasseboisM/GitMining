package data.service.stat;

import common.exception.TargetNotFoundException;

/**
 * 本接口用于获取用于构造UserRank对象的JSON字符串
 * @author River
 */
public interface UserStatGetter {

	public String getUserRanks(String login) throws TargetNotFoundException;
}
