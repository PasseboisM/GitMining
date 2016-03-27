package chart_data;

import java.util.List;

/**
 * ---暂时不可使用---
 * TODO 不清楚是指哪一种数据的统计，也许现在存储的数据里没有？
 * 
 * 
 * 用户的访问量。
 * 使用折线图
 * */


//public 
class GistsPerUser {
	//随访客数量的增加的用户的数量
	public List<Gist> users;

}
/**
 * 访客数量以10为间隔递增
 * */
class Gist {
	int gists;
	int gap = 10;//访客量间隔
}
