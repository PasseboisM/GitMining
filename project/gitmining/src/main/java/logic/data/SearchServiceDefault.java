package logic.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		List<RepositoryMin> matched = new LinkedList<>();
		List<Repository> result = new ArrayList<>(500);
		
		//TODO Not using the relation index really
		for(RepositoryMin minInfo:minInfoList) {
			if(repoMatcher.match(minInfo, params)>0.0) {
				matched.add(minInfo);
			}
		}
		
		// TODO try with multi-threads
		for(RepositoryMin matches:matched) {
			result.add(dataGetter.getSpecificRepo(matches));
		}
		
		result.sort(params.getSortStandard().getComparator());
		
		return result;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam params) throws NetworkException, DataCorruptedException {

		List<GitUserMin> minInfo = minInfoManager.getUserMin();
		List<GitUserMin> matched = new LinkedList<>();
		List<GitUser> result = new ArrayList<>();
		
		//TODO Not using the relation index really
		for(GitUserMin min:minInfo) {
			if(userMatcher.match(min, params)>0) {
				matched.add(min);
			}
		}
		
		// TODO try with multi-threads
		for(GitUserMin matches:matched) {
			result.add(dataGetter.getSpecificGitUser(matches));
		}
		
		result.sort(params.getSortStandard().getComparator());
		
		return result;
	}
	
	public SearchServiceDefault() {
		minInfoManager = MinInfoManager.getInstance();
		dataGetter = DataServiceFactory.getInstance().getSpecificDataGetter();
	}

}
