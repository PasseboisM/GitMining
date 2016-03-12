package logic.data;

import java.util.ArrayList;
import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import logic.service.GeneralGetter;

public class GeneralGetterDefault implements GeneralGetter {
	
	private MinInfoManager minInfoManager = null;
	
	private SpecificDataGetter dataGetter = null;
	
	@Override
	public List<Repository> getRepositories(int page, int numPerPage,
			RepoSortStadard sortStandard) throws NetworkException, DataCorruptedException {
		int leftEnd = (page-1) * numPerPage, rightEnd = page * numPerPage;
		List<RepositoryMin> repoMinInfo = minInfoManager.getRepoMin();
		
		assert leftEnd < repoMinInfo.size():"请求数目过多，超过数据总数";
		
		repoMinInfo.sort(sortStandard.getComparator());
		List<RepositoryMin> required = repoMinInfo.subList(leftEnd,
				rightEnd>repoMinInfo.size()? repoMinInfo.size():rightEnd);
		
		//TODO get Repository with multi-thread?
		List<Repository> result = new ArrayList<>(numPerPage);
		for(RepositoryMin minInfo:required) {
			result.add(dataGetter.getSpecificRepo(minInfo));
		}

//		result.sort(sortStandard.getComparator());
		
		return result;
	}

	@Override
	public int getNumOfRepositories() {
		return minInfoManager.getRepoNum();
	}

	@Override
	public int getNumOfUsers() {
		return minInfoManager.getUserNum();
	}

	/**
	 * 迭代一不需要此功能
	 * @throws DataCorruptedException 
	 * @throws NetworkException 
	 */
	@Override
	public List<GitUser> getUsers(int page, int numPerPage,
			UserSortSandard sortStandard) throws NetworkException, DataCorruptedException {
		int leftEnd = (page-1) * numPerPage, rightEnd = page * numPerPage;
		List<GitUserMin> totalList = minInfoManager.getUserMin();
		
		assert leftEnd < totalList.size():"请求数目过多，超过数据总数";
		
		totalList.sort(sortStandard.getComparator());
		/*
		 * List<RepositoryMin> required = repoMinInfo.subList(leftEnd,
				rightEnd>repoMinInfo.size()? repoMinInfo.size():rightEnd);
		
		//TODO get Repository with multi-thread?
		List<Repository> result = new ArrayList<>(numPerPage);
		for(RepositoryMin minInfo:required) {
			result.add(dataGetter.getSpecificRepo(minInfo));
		}
		 */
		List<GitUserMin> required = totalList.subList(leftEnd,
				(rightEnd>totalList.size())?totalList.size():rightEnd);
		List<GitUser> result = new ArrayList<>(numPerPage);
		for(GitUserMin min:required) {
			result.add(dataGetter.getSpecificGitUser(min));
		}
		return result;
	}
	
	public GeneralGetterDefault() {
		minInfoManager = MinInfoManager.getInstance();
		dataGetter = DataServiceFactory.getInstance().getSpecificDataGetter();
	}

}
