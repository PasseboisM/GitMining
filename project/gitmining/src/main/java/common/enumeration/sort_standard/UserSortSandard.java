package common.enumeration.sort_standard;

import java.util.Comparator;

import common.service.GitUserMin;

/**
 * 
 * @author xjh14
 * GitUserMin排序方式，表示各种排序策略
 * TODO 似乎Comparator里面的比较顺序是反的？
 */
public enum UserSortSandard {
	
	NO_SORT(new Comparator<GitUserMin>() {
		public int compare(GitUserMin arg0, GitUserMin arg1) {
			return 0;
		}	
	}),
	FOLLOWER_DESCENDING(new Comparator<GitUserMin>() {
		public int compare(GitUserMin arg0, GitUserMin arg1) {
			 return arg0.getFollowers()-arg1.getFollowers();
		}	
	}),
	;//TODO More compare standards
	
	
	private Comparator<GitUserMin> cpr;
	
	UserSortSandard(Comparator<GitUserMin> cpr) {
		this.cpr = cpr;
	}
	
	public Comparator<GitUserMin> getComparator() {
		return cpr;
	}
	
}
