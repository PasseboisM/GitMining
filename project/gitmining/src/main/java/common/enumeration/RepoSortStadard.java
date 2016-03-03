package common.enumeration;

import java.util.Comparator;

import common.service.RepositoryMin;

/**
 * @author River
 * RepositoryMin排序方式
 */
public enum RepoSortStadard {

	NO_SORT(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return 0;
		}	
	}),
	STARS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return arg0.getStars()-arg1.getStars();
		}	
	}),
	FORKS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			 return arg0.getForkNum()-arg1.getForkNum();
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
