package network.api.service;

import common.service.RepositoryMin;

public interface RepoApiMaker {
	
	/**
	 * 获取指定仓库的详细数据URL
	 * JSON对应：RepositoryBean,RepositoryMinBean
	 */
	public String makeRepoInfoApi(RepositoryMin source);
	
	/**
	 * 获取指定仓库的详细数据URL
	 * JSON对应：RepositoryBean,RepositoryMinBean
	 */
	public String makeRepoInfoApi(String fullName);
	
	/**
	 * 获取仓库数据开始之处：全部仓库的fullName的URL
	 * JSON对应：List<String>
	 */
	public String makeRepoNamesApi();
	
	/**
	 * 获取指定仓库的全部Contributor的登录名的URL
	 * JSON对应：List<String>
	 */
	public String makeRepoContributorLoginsApi(String repoFullName);
	
	/**
	 * 生成可获得指定仓库全部版本（branches）信息的URL
	 * JSON对应：List<BranchBean>
	 */
	public String makeBranchesApi(RepositoryMin source);
	
	/**
	 * 生成可获得指定仓库全部版本（branches）信息的URL
	 * JSON对应：List<BranchBean>
	 */
	public String makeBranchesApi(String fullName);
}
