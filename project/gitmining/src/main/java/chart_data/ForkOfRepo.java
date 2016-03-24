package chart_data;

import java.util.List;

/**
 * 不同fork量的项目数量分布，0-1000为一个梯度，1000-2000为一个梯度，2000以上为一个梯度。
 * 使用柱状图
 * */

public class ForkOfRepo {
	
	//项目的fork量
	public List<Integer> fork;
	
	//不同梯度的项目数量
	public List<BarInForkOfRepo> numOfRepo;

}
/**
 * 定义fork量梯度的范围
 * */
class BarInForkOfRepo {
	int lowerBound;
	int higherBound;
	int numOfRepos;
}