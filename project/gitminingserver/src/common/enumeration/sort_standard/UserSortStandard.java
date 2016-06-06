package common.enumeration.sort_standard;

import java.util.Comparator;

import common.service.GitUserMin;

/**
 * 
 * @author xjh14
 * GitUserMin排序方式，表示各种排序策略
 */
public enum UserSortStandard {
	
	NO_SORT(new Comparator<GitUserMin>() {
		public int compare(GitUserMin arg0, GitUserMin arg1) {
			return 0;
		}	
	}),
	FOLLOWER_DESCENDING(new Comparator<GitUserMin>() {
		public int compare(GitUserMin arg0, GitUserMin arg1) {
			 return arg1.getFollowers()-arg0.getFollowers();
		}	
	}),
	;
	
	
	private Comparator<GitUserMin> cpr;
	
	UserSortStandard(Comparator<GitUserMin> cpr) {
		this.cpr = cpr;
	}
	
	public Comparator<GitUserMin> getComparator() {
		return cpr;
	}
	
	public static UserSortStandard getStandard(String name) {
		assert name!=null;
		
		if (name.equalsIgnoreCase("no")||name.equalsIgnoreCase("no_sort")||name.equals("")) {
			return UserSortStandard.NO_SORT;
		} else {
			return UserSortStandard.FOLLOWER_DESCENDING;
		}
	}
	
}
