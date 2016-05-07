package common.enumeration.sort_standard;

import java.util.Comparator;

import org.kohsuke.github.GHRepositorySearchBuilder.Sort;

import common.service.RepositoryMin;

/**
 * @author xjh14
 * RepositoryMin排序方式
 */
public enum RepoSortStadard {

	NO_SORT(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return 0;
		}	
	},null),
	STARS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return arg1.getStargazers_count()-arg0.getStargazers_count();
		}	
	},Sort.STARS),
	FORKS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			 return arg1.getForks_count()-arg0.getForks_count();
		}	
	},Sort.FORKS),
	;//TODO More compare standards
	
	
	private Comparator<RepositoryMin> cpr;
	private Sort sort;
	
	RepoSortStadard(Comparator<RepositoryMin> cpr,Sort sort) {
		this.cpr = cpr;
		this.sort = sort;
	}
	
	public Comparator<RepositoryMin> getComparator() {
		return cpr;
	}
	
	public Sort getSort(){
		return sort;
	}
}
