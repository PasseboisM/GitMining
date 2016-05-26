package network.api.service;

import common.service.GitUserMin;

/**
 * @author xjh14
 * 提供通过不同参数生成查询GitUser信息的API链接
 */
public interface UserApiMaker {

	public String makeUserAPI(GitUserMin source);
	
	public String makeUserAPI(String login);
	
	/**
	 * 获取所有User的LOGIN信息；<br />
	 * 数组中的每个URL都指向包含了50个Login
	 * @return URL数组（大小应该为1355--2016/4/7）
	 */
	public String[] allUserAPIs();
}
