package logic.calc.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chart_data.radar.RadarDatas;
import chart_data.radar.UserRanks;
import logic.calc.python.PythonRunner;
//TODO 之后会移位置
public class UserStatisticsUtil {
	private static final String SINGLE_USER_FILE =  "statistic_single_user_rank.py";
	@Deprecated
	public static RadarDatas getSingleUserPoints(String name) throws IOException, InterruptedException{
		List<String> strResult = PythonRunner.runPython(SINGLE_USER_FILE, name);
		RadarDatas radarDatas = new UserRanks();
		List<String> headers = new ArrayList<>(Arrays.asList("followers", "following", "public_gists", "public_repos"));
		for (int i = 0; i < headers.size(); i++) {
			radarDatas.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
		}
		return radarDatas;
	}
}
