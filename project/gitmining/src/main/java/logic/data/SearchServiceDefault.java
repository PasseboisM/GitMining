package logic.data;

import java.util.List;

import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;
import data.service.DataServiceFactory;
import data.service.SpecificDataGetter;
import logic.service.SearchService;

public class SearchServiceDefault implements SearchService {

	private MinInfoManager minInfoManager = null;
	
	private SpecificDataGetter dataGetter = null;
	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SearchServiceDefault() {
		minInfoManager = MinInfoManager.getInstance();
		dataGetter = DataServiceFactory.getInstance().getSpecificDataGetter();
	}

}
