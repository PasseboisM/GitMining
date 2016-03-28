package chart_data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Type:GeneralStatistics
 * 内部包含了按照fork number进行统计、划分区间的Repository分布情况。
 * */

public class ForkNumberRanges {
	
	private List<ForkNumberRange> repositoryRanges = new LinkedList<>();
	private int gap = 100;
	
	
	public void addRange(int lowerBound, int higherBound, int numOfRepos) {
		repositoryRanges.add(new ForkNumberRange(lowerBound, higherBound, numOfRepos));
	}
	
	/**
	 * 获取所有的ForkNumberRange<br />
	 * 迭代器保证提供的区间<strong>从小到大排序</strong>。
	 */
	public Iterator<ForkNumberRange> getRepositoryRanges() {
		return repositoryRanges.listIterator();
	}
	
	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public class ForkNumberRange {
		public final int lowerBound;
		public final int higherBound;
		public final int numOfRepos;
		public ForkNumberRange(int lowerBound, int higherBound, int numOfRepos) {
			super();
			this.lowerBound = lowerBound;
			this.higherBound = higherBound;
			this.numOfRepos = numOfRepos;
		}
	}
}
