package chart_data;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type: GeneralStatistics<br/>
 * 系统中仓库数量关于其Stars_Count的分布。<br/>
 * 建议区间长度：500
 * */

public class StarCountRanges {
	
	private List<StarCountRange> counts = new LinkedList<>();

	private int gap = 500;
	
	public void addRange(int lowerStar, int higherStar, int numOfRepos) {
		counts.add(new StarCountRange(lowerStar, higherStar, numOfRepos));
	}
	
	public int getNumOfRanges() {
		return counts.size();
	}
	
	/**
	 * 获取所有仓库数量关于其Stars_Count的分布，<br/>
	 * 迭代器给出的顺序为<strong>区间左端点从小到大</strong>的顺序。
	 */
	public Iterator<StarCountRange> getRanges() {
		
		counts.sort(new Comparator<StarCountRange>() {
			@Override
			public int compare(StarCountRange o1, StarCountRange o2) {
				return o1.lowerStar - o2.lowerStar;
			}
		});
		
		return counts.listIterator();
	}
	
	
	
	/**
	 * 获取建议的区间长度。（也可以自行调整之）
	 */
	public int getGap() {
		return gap;
	}
	
	public void setGap(int gap) {
		this.gap = gap;
	}



	public class StarCountRange {
		public final int lowerStar;
		public final int higherStar;
		public final int numOfRepos;
		public StarCountRange(int lowerStar, int higherStar, int numOfRepos) {
			super();
			this.lowerStar = lowerStar;
			this.higherStar = higherStar;
			this.numOfRepos = numOfRepos;
		}
	}
	

}

