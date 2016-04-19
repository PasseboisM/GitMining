package logic.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import logic.data.search.RepoSearchStrategyDefault;
import logic.data.search.UserSearchStrategyDefault;
import logic.data.search.service.RepositorySearchStrategy;
import logic.data.search.service.UserSearchStrategy;
import logic.service.SearchService;

public class SearchServiceDefault implements SearchService {
	
	private UserSearchStrategy userMatcher = new UserSearchStrategyDefault();
	private RepositorySearchStrategy repoMatcher = new RepoSearchStrategyDefault();
	
	private MinInfoManager minInfoManager = null;
	
	private SpecificDataGetter dataGetter = null;
	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam params) throws NetworkException, DataCorruptedException {
		List<RepositoryMin> minInfoList = minInfoManager.getRepoMin();
		for(String s:params.getKeywords()) {
			System.out.println(s);
		}
		class Pair{

			double relation;
			RepositoryMin content;
			public Pair(double relation, RepositoryMin content) {
				super();
				this.relation = relation;
				this.content = content;
			}
		}
		List<Pair> matched = new ArrayList<>();
		List<Repository> result = new ArrayList<>(500);
		
		
		double relationIndex = 0;
		for(RepositoryMin minInfo:minInfoList) {
			relationIndex = repoMatcher.match(minInfo, params);
			if(relationIndex>0.0) {
				matched.add(new Pair(relationIndex, minInfo));
			}
		}

		matched.sort(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return (int) (100*(o2.relation-o1.relation));
			}
		});
		
		// TODO try with multi-threads
		for(Pair matches:matched) {
			result.add(dataGetter.getSpecificRepo(matches.content));
		}
		
		result.sort(params.getSortStandard().getComparator());
		
		return result;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam params) throws NetworkException, DataCorruptedException {

		List<GitUserMin> minInfo = minInfoManager.getUserMin();
		
		class Pair{
			double relation;
			GitUserMin content;
			public Pair(double relation, GitUserMin content) {
				super();
				this.relation = relation;
				this.content = content;
			}
		}
		List<Pair> matched = new LinkedList<>();
		List<GitUser> result = new ArrayList<>();
		
		double relationIndex = 0.0;
		for(GitUserMin min:minInfo) {
			relationIndex = userMatcher.match(min, params);
			if(relationIndex>0.0) {
				matched.add(new Pair(relationIndex, min));
			}
		}
		
		matched.sort(new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				return (int) (100*(o2.relation-o1.relation));
			}
		});
		
		// TODO try with multi-threads
		for(Pair matches:matched) {
			result.add(dataGetter.getSpecificGitUser(matches.content));
		}
		
		result.sort(params.getSortStandard().getComparator());
		
		return result;
	}
	
	public SearchServiceDefault() {
		minInfoManager = MinInfoManager.getInstance();
		dataGetter = DataServiceFactory.getInstance().getSpecificDataGetter();
	}

}
