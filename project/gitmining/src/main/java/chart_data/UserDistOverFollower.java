package chart_data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics
 * 内部包含了拥有不同Follower数的User分布状况。
 * 
 * */

public class UserDistOverFollower {
	//随追随者数量的增加的用户的数量
	private List<FollowerNumberRange> ranges = new LinkedList<>();
	private int gap = 10;//追随者数量的间隔
	/**
	 * 向本数据中加入新的区间（注意区间要从小到大加入）
	 */
	public void addNewRange(int lowerRange, int higherRange, int numOfUsers) {
		ranges.add(new FollowerNumberRange(lowerRange, higherRange, numOfUsers));
	}
	
	public int getNumOfRange(){
		return ranges.size();
	}
	
	/**
	 * 获取所有UserRange。<br />
	 * 数据迭代器保证区间<strong>按顺序从小到大提供</strong>。
	 */
	public Iterator<FollowerNumberRange> getUserRanges() {
		return ranges.listIterator();
	}
	
	/**
	 * 添加数据或使用数据时，可以参考默认建议数据区间长度。<br />
	 * （也可以不采用默认区间长度）
	 */
	public int getGap() {
		return gap;
	}
	
	public void setGap(int newValue) {
		gap = newValue;
	}
	
	/**
	 * 追随者数量建议以10为间隔递增
	 * */
	public class FollowerNumberRange {
		public final int lowerRange;
		public final int higherRange;
		public final int numOfUsers;
		public FollowerNumberRange(int lowerRange, int higherRange, int numOfUsers) {
			super();
			this.lowerRange = lowerRange;
			this.higherRange = higherRange;
			this.numOfUsers = numOfUsers;
		}

	}

}

