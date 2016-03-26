package chart_data;

import java.util.List;

/**
 * 对单个项目的评分，包括对项目的contributers、commit、issue和pull request的评分。[0,1]。
 * 使用雷达图
 * */

public class ScoreOfRepo {
	
	//对项目forks、open_issues、size、subscribers_count、watchers的评分
	public List<Double> points;
	
	//文字描述列表
	public List<String> labels;

}
