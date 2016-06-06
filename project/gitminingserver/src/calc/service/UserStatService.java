package calc.service;

import chart_data.radar.UserRanks;
import common.service.GitUser;

public interface UserStatService {

	
	public UserRanks getRanks(GitUser u);
}
