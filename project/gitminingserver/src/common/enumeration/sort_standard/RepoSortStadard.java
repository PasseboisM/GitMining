package common.enumeration.sort_standard;

import java.util.Comparator;

import common.exception.TargetNotFoundException;
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
	},"no"),
	STARS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			return arg1.getStargazers_count()-arg0.getStargazers_count();
		}	
	},"stars"),
	FORKS_DESCENDING(new Comparator<RepositoryMin>() {
		public int compare(RepositoryMin arg0, RepositoryMin arg1) {
			 return arg1.getForks_count()-arg0.getForks_count();
		}	
	},"forks"),
	;
	
	
	private Comparator<RepositoryMin> cpr;
	private String sort;
	
	RepoSortStadard(Comparator<RepositoryMin> cpr,String sort) {
		this.cpr = cpr;
		this.sort = sort;
	}
	
	public Comparator<RepositoryMin> getComparator() {
		return cpr;
	}
	
	public String getName(){
		return sort;
	}
	
	public static RepoSortStadard fromName(String sortName) throws TargetNotFoundException{
		for (RepoSortStadard sort:RepoSortStadard.values()) {
			if (sort.getName().equals(sortName)) {
				return sort;
			}
		}
		throw new TargetNotFoundException();
	}
}
