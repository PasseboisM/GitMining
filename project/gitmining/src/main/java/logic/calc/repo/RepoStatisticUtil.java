package logic.calc.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.calc.python.PythonRunner;

public class RepoStatisticUtil {
		private static final String SINGLE_USER_FILE =  "statistics_user.py";
		public static List<Double> getSingleUserPoints(String login) throws IOException, InterruptedException{
			List<String> strResult = PythonRunner.runpython(SINGLE_USER_FILE, login);
			List<Double> doubleResult = new ArrayList<>();
			for (String string : strResult) {
				doubleResult.add(Double.parseDouble(string));
			}
			return doubleResult;
		}
}
