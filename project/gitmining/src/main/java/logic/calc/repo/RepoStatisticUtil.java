package logic.calc.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chart_data.radar.RadarDatas;
import chart_data.radar.RepositoryRanks;
import logic.calc.python.PythonRunner;

public class RepoStatisticUtil {
		private static final String SINGLE_USER_FILE =  "statistics_repo.py";
		public static RadarDatas getSingleUserPoints(String name) throws IOException, InterruptedException{
			List<String> strResult = PythonRunner.runpython(SINGLE_USER_FILE, name);
			RadarDatas radarDatas = new RepositoryRanks();
			List<String> headers = new ArrayList<>(Arrays.asList("forks", "open_issues", "size", "subscribers_count", "watchers"));
			for (int i = 0; i < headers.size(); i++) {
				radarDatas.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
			}
			return radarDatas;
		}
}
