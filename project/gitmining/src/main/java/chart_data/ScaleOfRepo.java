package chart_data;

import java.util.List;

/**
 * ---暂时不可用---
 * TODO 预计以当前有限样本统计结果参考价值不足，可能暂时不实现此统计
 * 
 * 
 * 不同大小团队规模的项目数量，如：团队人数在0-25人的项目有1000个.
 * 使用柱状图
 * */

public class ScaleOfRepo {
	
	//每个项目团队的人数
	public List<Integer> numOfPeople;
	
	//不同人数范围内项目的数量
	public List<PeopleNumInRepo> numOfRepo;

}
/**
 * 定义项目参与者数量范围
 * */
class PeopleNumInRepo {
	int lowerNum;
	int higherNum;
	int numOfRepos;
}
