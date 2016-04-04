package chart_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Type: GeneralStatistics<br />
 * 所有Repository的拥有者的类型分布统计。<br />
 * 预计只有User/Organization两种类型
 * */

public class UserDistOverType {
	
	public static final List<String> headers =  Arrays.asList("User","Organization");
	public static final String title = "用户类型统计图";
	
	private List<UserTypeCount> counts = new ArrayList<>(2);
	
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
		@Override
		public String toString() {
			return "UserTypeCount [type=" + type + ", count=" + count + "]";
		}
		
	}
	
}
