package common.enumeration;

import java.util.Comparator;

import common.service.RepositoryMin;

/**
 * @author xjh14
 * RepositoryMin排序方式
 * TODO 似乎Comparator里面的比较顺序是反的？
 */
public enum RepoSortStadard {

	NO_SORT(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return 0;
		}	
	}),
	STARS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return arg0.getStargazers_count()-arg1.getStargazers_count();
		}	
	}),
	FORKS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			 return arg0.getForks_count()-arg1.getForks_count();
		}	
	}),
	;//TODO More compare standards
	
	
	private Comparator<RepositoryMin> cpr;
	
	RepoSortStadard(Comparator<RepositoryMin> cpr) {
		this.cpr = cpr;
	}
	
	public Comparator<RepositoryMin> getComparator() {
		return cpr;
	}
}
