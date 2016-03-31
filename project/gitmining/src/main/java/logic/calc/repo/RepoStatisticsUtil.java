package logic.calc.repo;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import chart_data.radar.RepositoryRanks;
import common.service.Repository;
import logic.calc.python.PythonRunner;
import logic.calc.service.RepositoryStatisticsService;

public class RepoStatisticsUtil implements RepositoryStatisticsService{
	
		private static final Gson gson = new Gson();
		private static final String SINGLE_REPO_FILE =  "statistic_single_repo_rank.py";

		/**
		 * Dependency: statistic_single_repo_rank.py
		 * 	Python input: 
		 * 		1. Headers as JSON list
		 * 		2. Repository data in JSON format
		 * 	Python output:
		 * 		Rank results in the corresponding order as the header list.
		 */
		@Override
		public RepositoryRanks getRanks(Repository r) {
			List<String> headers = RepositoryRanks.defaultHeaders;
			String headerStr = gson.toJson(headers);
			String repoStr = gson.toJson(r);
			
			List<String> strResult = null;
			
			try {
				strResult = PythonRunner.runPython(SINGLE_REPO_FILE, headerStr, repoStr);
			} catch (IOException e) {
				//TODO Better catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RepositoryRanks radarDatas = new RepositoryRanks();
			for (int i = 0; i < headers.size(); i++) {
				radarDatas.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
			}
			return radarDatas;
		}
}
