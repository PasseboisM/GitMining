package network.api;

import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import network.api.service.SearchApiMaker;

public class SearchApiMakerGitMining implements SearchApiMaker {

	@Override
	public String makeSearchUserApi(UserSearchParam param,int page) {
		if(param==null) return null;
		String api = SEARCH_SITE+USER;
		String loginName = param.getLoginName();
		if(loginName==null) return null;
		String searchItems[] = loginName.trim().split(" ");
		if(searchItems[0].equals("")) return null;
		api+="?q=";
		for (int i = 0; i < searchItems.length; i++) {
			if (i!=0) {
				api+="+";
			}
			api+=searchItems[i];
		}
		return api+"&page="+page;
	}

	@Override
	public String makeSearchRepoApi(RepositorySearchParam param, int page) {
		if(param==null) return null;
		Language[] langs = param.getLangs();
		String[] keywords = param.getKeywords();
		RepoSortStadard sortStandard = param.getSortStandard();
		if(keywords==null)	return null;
		if(keywords[0].equals("")) return null;
		String api = SEARCH_SITE+REPO;
		api+="?q=";
		for (int i = 0; i < keywords.length; i++) {
			if (i!=0) {
				api+="+";
			}
			api+=keywords[i];
		}
		for (Language language : langs) {
			api+="+language:"+language.getName();
		}
		String sort = sortStandard.getSort();
		if(sort!=null)
			api+="&sort="+sort;
		return api+"&page="+page;
	}

}
