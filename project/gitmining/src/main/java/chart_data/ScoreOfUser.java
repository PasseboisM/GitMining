package chart_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 对单个用户的评分，包括对用户的followers、following、public_gists、public_repos的评分。[0,1]。 
 * 使用雷达图
 */

public class ScoreOfUser {
	// TODO 之后添加其他方面的评分
	// 对项目followers、following、public_gists、public_repos的评分
	public List<Double> marks;

	// 文字描述列表
	public List<String> headers;

	public ScoreOfUser(List<Double> marks, List<String> headers) {
		this.marks = marks;
		this.headers = headers;
	}

	public ScoreOfUser(List<Double> marks) {
		this.headers = new ArrayList<>(Arrays.asList("followers", "following", "public_gists", "public_repos"));
		this.marks = marks;
	}

}
