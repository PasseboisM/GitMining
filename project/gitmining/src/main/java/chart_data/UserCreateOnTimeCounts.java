package chart_data;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics<br />
 * 系统内用户注册时间分布，预期以年为单位。
 * */

public class UserCreateOnTimeCounts {

	private List<UserCreateOnTimeCount> counts = new LinkedList<>();
	
	public void addCreateCount(String time, int count) {
		counts.add(new UserCreateOnTimeCount(time, count));
	}
	
	public int getNumOfCount(){
		return counts.size();
	}
	
	/**
	 * 获取关于时间的用户创建数目分布，<br />
	 * 迭代器<strong>按照时间顺序先后</strong>给出各个时间段的条目。
	 */
	public Iterator<UserCreateOnTimeCount> getCounts() {
		
		counts.sort(new Comparator<UserCreateOnTimeCount>() {
			@Override
			public int compare(UserCreateOnTimeCount o1,
					UserCreateOnTimeCount o2) {
				return o1.time.compareTo(o2.time);
			}
		});
		
		return counts.listIterator();
	}
	
	public class UserCreateOnTimeCount {
		public final String time;
		public final int count;
		public UserCreateOnTimeCount(String time, int count) {
			this.time = time;
			this.count = count;
		}
	}

}