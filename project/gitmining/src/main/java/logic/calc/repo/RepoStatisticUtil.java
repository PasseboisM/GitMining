package logic.calc.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chart_data.RadarDatas;
import logic.calc.python.PythonRunner;

public class RepoStatisticUtil {
		private static final String SINGLE_USER_FILE =  "statistics_repo.py";
		public static RadarDatas getSingleUserPoints(String name) throws IOException, InterruptedException{
			List<String> strResult = PythonRunner.runpython(SINGLE_USER_FILE, name);
			List<Double> doubleResult = new ArrayList<>();
			for (String string : strResult) {
				doubleResult.add(Double.parseDouble(string));
			}
			List<String> headers = new ArrayList<>(Arrays.asList("forks", "open_issues", "size", "subscribers_count", "watchers"));
			return new RadarDatas(doubleResult, headers);
		}
}
