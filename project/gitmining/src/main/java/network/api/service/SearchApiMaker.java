package network.api.service;

import common.param_obj.UserSearchParam;

public interface SearchApiMaker {
	static final String SEARCH_SITE = "https://api.github.com/search";
	static final String USER = "/users";
	static final String REPO = "/repositories";
	public String makeSearchUserApi(UserSearchParam param,int page);
}
