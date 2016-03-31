package chart_data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics<br />
 * 所有Repository的拥有者的类型分布统计。<br />
 * 预计只有User/Organization两种类型
 * */

public class UserDistOverType {
	
	public List<UserTypeCount> counts = new LinkedList<>();
	public String title = "用户类型统计图";
	
	public void addCount(String type, int count) {
		counts.add(new UserTypeCount(type, count));
	}
	
	public Iterator<UserTypeCount> getCounts() {
		return counts.listIterator();
	}

	public class UserTypeCount {
		public final String type;
		public final int count;
		public UserTypeCount(String type, int count) {
			super();
			this.type = type;
			this.count = count;
		}
	}
	
}
