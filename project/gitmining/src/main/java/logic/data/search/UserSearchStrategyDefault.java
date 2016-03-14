package logic.data.search;

import common.param_obj.UserSearchParam;
import common.service.GitUserMin;
import logic.data.search.service.UserSearchStrategy;

public class UserSearchStrategyDefault implements UserSearchStrategy {

	@Override
	public double match(GitUserMin obj, UserSearchParam params) {
		if(nameMatch(obj.getLogin(),params.getLoginName())||nameMatch(obj.getName(),params.getLoginName())) {
			return 1.0;
		}
		return 0.0;
	}

	
	private boolean nameMatch(String original,String searched) {
		return original.toLowerCase().contains(searched.toLowerCase());
	}
	
}
