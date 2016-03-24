package chart_data;

import java.util.List;

/**
 * 项目拥有的用户的数量的累积。
 * 使用折线图
 * */

public class RepoPerUser {
	//随项目数量的增加的用户的数量
	public List<Repository> users;

}
/**
 * 项目数量以10为间隔递增
 * */
class Repository {
	int repositories;
	int gap = 10;//项目数量的间隔
}
