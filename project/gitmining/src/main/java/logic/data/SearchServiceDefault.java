package logic.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import logic.service.SearchService;

public class SearchServiceDefault implements SearchService {

	private MinInfoManager minInfoManager = null;
	
	private SpecificDataGetter dataGetter = null;
	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam params) {
		List<RepositoryMin> minInfoList = minInfoManager.getRepoMin();
		List<RepositoryMin> matched = new LinkedList<>();
		List<Repository> result = new ArrayList<>(500);
		
		for(RepositoryMin minInfo:minInfoList) {
			if(params.matches(minInfo)) {
				matched.add(minInfo);
			}
		}
		
		// TODO try with multi-threads
		for(RepositoryMin matches:matched) {
			result.add(dataGetter.getSpecificRepo(matches));
		}
		
		return result;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam params) {

		List<GitUserMin> minInfo = minInfoManager.getUserMin();
		List<GitUserMin> matched = new LinkedList<>();
		List<GitUser> result = new ArrayList<>();
		
		for(GitUserMin min:minInfo) {
			if(params.matches(min)) {
				matched.add(min);
			}
		}
		
		// TODO try with multi-threads
		for(GitUserMin matches:matched) {
			result.add(dataGetter.getSpecificGitUser(matches));
		}
		
		return result;
	}
	
	public SearchServiceDefault() {
		minInfoManager = MinInfoManager.getInstance();
		dataGetter = DataServiceFactory.getInstance().getSpecificDataGetter();
	}

}
