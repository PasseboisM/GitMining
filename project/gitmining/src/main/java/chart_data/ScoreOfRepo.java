package chart_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对单个项目的评分，包括对项目的contributers、commit、issue和pull request的评分。[0,1]。
 * 使用雷达图
 * */

public class ScoreOfRepo {
	//TODO 之后添加其他方面的评分
	//对项目forks、open_issues、size、subscribers_count、watchers的评分
	public List<Double> marks;
	
	//文字描述列表
	public List<String> headers;

	public ScoreOfRepo(List<Double> marks, List<String> headers) {
		this.marks = marks;
		this.headers = headers;
	}

	public ScoreOfRepo(List<Double> marks) {
		this.headers = new ArrayList<>(Arrays.asList("forks", "open_issues", "size","subscribers_count","watchers"));
		this.marks = marks;
	}
	
	

}
