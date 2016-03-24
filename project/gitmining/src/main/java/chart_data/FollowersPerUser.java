package chart_data;

import java.util.List;

/**
 * 用户的追随者数量。
 * 使用折线图
 * */

public class FollowersPerUser {
	//随追随者数量的增加的用户的数量
	public List<Follower> users;

}
/**
 * 追随者数量以10为间隔递增
 * */
class Follower {
	int followers;
	int gap = 10;//追随者数量的间隔
}
