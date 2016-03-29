package logic.calc.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chart_data.radar.RepositoryRanks;
import common.service.Repository;
import logic.calc.python.PythonRunner;
import logic.calc.service.RepositoryStatisticsService;

public class RepoStatisticUtil implements RepositoryStatisticsService{
	
		private static final String SINGLE_REPO_FILE =  "statistic_single_repo_rank.py";


		@Override
		public RepositoryRanks getRanks(Repository r) {
			List<String> strResult = null;
			try {
				strResult = PythonRunner.runPython(SINGLE_REPO_FILE, r.getForks_count()+"",r.getOpen_issues()+"",r.getSize()+"",r.getSubscribers_count()+"",r.getWatchers()+"");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			RepositoryRanks radarDatas = new RepositoryRanks();
			List<String> headers = new ArrayList<>(Arrays.asList("forks", "open_issues", "size", "subscribers_count", "watchers"));
			for (int i = 0; i < headers.size(); i++) {
				radarDatas.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
			}
			return radarDatas;
		}
}
