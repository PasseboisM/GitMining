package chart_data;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics<br />
 * 系统内用户注册时间分布，预期以年为单位。
 * */

public class UserDistOverCreateTime {

	private List<UserCreateOnTimeCount> counts = new LinkedList<>();
	
	public void addCreateCount(String timeLo, String timeHi, int count) {
		counts.add(new UserCreateOnTimeCount(timeLo, timeHi, count));
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
				return o1.timeLo.compareTo(o2.timeLo);
			}
		});
		
		return counts.listIterator();
	}
	
	public class UserCreateOnTimeCount {
		public final String timeLo;
		public final String timeHi;
		public final int count;
		
		
		public UserCreateOnTimeCount(String timeLo, String timeHi, int count) {
			this.timeLo = timeLo;
			this.timeHi = timeHi;
			this.count = count;
		}
		
		@Override
		public String toString() {
			return "UserCreateOnTimeCount [timeLo=" + timeLo + ", timeHi="
					+ timeHi + ", count=" + count + "]";
		}
		
	}

}