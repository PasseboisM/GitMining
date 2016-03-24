package chart_data;

import java.util.List;

/**
 * 用户的访问量。
 * 使用折线图
 * */

public class GistsPerUser {
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
