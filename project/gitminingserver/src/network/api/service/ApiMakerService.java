package network.api.service;

import network.api.ApiMakerGitMining;

/**
 * 统一获取API模块的各种API Maker的抽象类。<br />
 * 当需要获取仓库/用户/网络测试相关URL时，应该先从本类获取对应的API生成器。
 * @author xjh14
 */
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
	
	public abstract SearchApiMaker getSearchApiMaker();
	
	public abstract AnalysisApiMaker getAnalysisApiMaker();
	
	public static ApiMakerService getInstance() {
		return new ApiMakerGitMining();
	}
	
}
