package calc.service;

import chart_data.radar.RepositoryRanks;
import common.service.Repository;

public interface RepoStatService {

	public RepositoryRanks getRanks(Repository r);
	
}
