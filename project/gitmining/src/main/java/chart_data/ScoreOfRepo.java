package chart_data;

import java.util.List;

/**
 * 对单个项目的评分，包括对项目的contributers、commit、issue和pull request的评分。[0,1]。
 * 使用雷达图
 * */

public class ScoreOfRepo {
	
	//对项目contributers的评分
	public List<Double> contributers;
	
	//对项目commit值的评分
	public List<Double> commits;
	
	//对项目issue的评分
	public List<Double> issue;
	
	//对项目pull request的评分
	public List<Double> pull_request;

}
