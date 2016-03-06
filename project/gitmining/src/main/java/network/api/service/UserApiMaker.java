package network.api.service;

import common.service.GitUserMin;

/**
 * @author xjh14
 * 提供通过不同参数生成查询GitUser信息的API链接
 */
public interface UserApiMaker {

	public String makeUserAPI(GitUserMin source);
	
	public String makeUserAPI(String login);
}