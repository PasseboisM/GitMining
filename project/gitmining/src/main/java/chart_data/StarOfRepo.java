package chart_data;

import java.util.List;

/**
 * 不同star量的项目数量分布，0-1000为一个梯度，1000-2000为一个梯度，2000以上为一个梯度.
 * 使用柱状图
 * */

public class StarOfRepo {
	
	//所有项目的star量
	public List<Integer> star;
		
	//不同梯度的项目数量
	public List<BarInStarOfRepo> numOfRepo;

}
/**
 * 定义star量梯度的范围
 * */
class BarInStarOfRepo {
	int lowerStar;
	int higherStar;
	int numOfRepos;
}
