package network.api;

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

}
