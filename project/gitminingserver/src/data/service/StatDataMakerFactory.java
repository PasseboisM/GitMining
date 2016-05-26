package data.service;

import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;

public interface StatDataMakerFactory {

	/**
	 * 获取可用于对整体数据（全体Repo/User）进行特征分析、取得结果的接口
	 */
	public GeneralStatGetter getGeneralStatGetter();
	
	/**
	 * 获取可对单个GitUser进行数据特征分析、取得结果的接口
	 */
	public UserStatGetter getUserStatGetter();
	
	/**
	 * 获取可对单个Repository进行数据特征分析、取得结果的接口
	 */
	public RepoStatGetter getRepoStatGetter();
	
}
