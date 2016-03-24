package network.api.service;

import network.api.ApiMakerGitMining;

public abstract class ApiMakerService {

	/**
	 * 获取Github项目的api生成器
	 * @return api生成器
	 */
	public abstract RepoApiMaker getRepoApiMaker();
	
	/**
	 * 获取Github用户的api生成器
	 * @return api生成器
	 */
	public abstract UserApiMaker getUserApiMaker();
	
	/**
	 * 获取测试网络用的api生成器
	 * @return api生成器
	 */
	public abstract TestConnectionApiMaker getTestConnectionApiMaker();
	
	public static ApiMakerService getInstance() {
		return new ApiMakerGitMining();
	}
	
}
