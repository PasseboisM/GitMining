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
	
	
	
	public static ApiMakerService getInstance() {
		return new ApiMakerGitMining();
	}
	
	/**
	 * 获取网络测试对象实例，用于检测网络连接是否良好
	 * @return 网络测试对象实例
	 */
	public static NetworkTester[] getNetworkTesters() {
		NetworkTester[] result = {
				new NetworkTester("http://gitmining.net/api/user/rubinius/item/login", "rubinius"),
				new NetworkTester("http://gitmining.net/api/repository/ai/r18n/item/created_at", "2008-12-24T11:15:15Z")
		};
		return result;
	}
}
