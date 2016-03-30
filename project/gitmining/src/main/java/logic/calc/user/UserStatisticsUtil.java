package logic.calc.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import common.service.GitUser;

import chart_data.radar.RadarDatas;
import chart_data.radar.UserRanks;
import logic.calc.python.PythonRunner;
//TODO 之后会移位置
public class UserStatisticsUtil {
	
	private static final Gson gson = new Gson();
	private static final String SINGLE_USER_FILE =  "statistic_single_user_rank.py";

	//TODO 应减少界面层可见参数数目。
	public static UserRanks getSingleUserPoints(GitUser u) throws IOException, InterruptedException{
		
		List<String> headers = UserRanks.defaultHeaders;
		String headerStr = gson.toJson(headers);
		String userStr = gson.toJson(u);
		
		List<String> strResult = PythonRunner.runPython(SINGLE_USER_FILE, headerStr, userStr);
		UserRanks userRanks = new UserRanks();

		for (int i = 0; i < headers.size(); i++) {
			userRanks.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
		}
		return userRanks;
	}
}
