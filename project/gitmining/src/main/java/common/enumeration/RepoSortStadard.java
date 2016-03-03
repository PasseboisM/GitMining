package common.enumeration;

import java.util.Comparator;

import common.service.Repository;

/**
 * @author River
 * Repository排序方式
 */
public enum RepoSortStadard {

	NO_SORT(new Comparator<Repository>() {
		public int compare(Repository arg0, Repository arg1) {
			return 0;
		}	
	}),
	STARS_DESCENDING(new Comparator<Repository>() {
		public int compare(Repository arg0, Repository arg1) {
			// return arg0.getStars()>arg0.getStars();
			// TODO 接口还没有准备好
			return 0;
		}	
	}),
	FORKS_DESCENDING(new Comparator<Repository>() {
		public int compare(Repository arg0, Repository arg1) {
			// return arg0.getForkNumber()>arg0.getForkNumber();
			// TODO 接口还没有准备好
			return 0;
		}	
	}),
	;//TODO More compare standards
	
	
	private Comparator<Repository> cpr;
	
	RepoSortStadard(Comparator<Repository> cpr) {
		this.cpr = cpr;
	}
	
	public Comparator<Repository> getComparator() {
		return cpr;
	}
}
