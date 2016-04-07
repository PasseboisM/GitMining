package chart_data;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics<br />
 * 系统内仓库创建时间分布，预期以年为单位。
 * 
 * TODO 应该与UserCreateOnTimeCounts共享一个父类
 * */

public class RepoDistOverCreateTime {
	
	private List<RepoCreateOnTimeCount> counts = new LinkedList<>();
	
	public void addCreateCount(String timeLo, String timeHi, int count) {
		counts.add(new RepoCreateOnTimeCount(timeLo, timeHi, count));
	}
	
	/**
	 * 获取关于时间的仓库创建数目分布，<br />
	 * 迭代器<strong>按照时间顺序先后</strong>给出各个时间段的条目。
	 */
	public Iterator<RepoCreateOnTimeCount> getCounts() {
		
		counts.sort(new Comparator<RepoCreateOnTimeCount>() {
			@Override
			public int compare(RepoCreateOnTimeCount o1,
					RepoCreateOnTimeCount o2) {
				return o1.timeLo.compareTo(o2.timeLo);
			}
		});
		
		return counts.listIterator();
	}
	
	public int getNumOfCounts(){
		return counts.size();
	}
	
	public class RepoCreateOnTimeCount {
		public final String timeLo;
		public final String timeHi;
		public final int count;
		public RepoCreateOnTimeCount(String timeLo, String timeHi, int count) {
			this.timeLo = timeLo;
			this.timeHi = timeHi;
			this.count = count;
		}
		@Override
		public String toString() {
			return "RepoCreateOnTimeCount [timeLo=" + timeLo + ", timeHi="
					+ timeHi + ", count=" + count + "]";
		}
	}

}
