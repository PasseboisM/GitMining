package logic.calc.user;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import common.service.GitUser;
import chart_data.radar.UserRanks;
import logic.calc.python.PythonRunner;
import logic.calc.service.UserStatisticsService;

public class UserStatisticsUtil implements UserStatisticsService {
	
	private static final Gson gson = new Gson();
	private static final String SINGLE_USER_FILE =  "statistic_single_user_rank.py";

	public UserRanks getRanks(GitUser u){
		
		List<String> headers = UserRanks.defaultHeaders;
		String headerStr = gson.toJson(headers);
		String userStr = gson.toJson(u);
		
		List<String> strResult = null;
		try {
			strResult = PythonRunner.runPython(SINGLE_USER_FILE, headerStr, userStr);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserRanks userRanks = new UserRanks();

		for (int i = 0; i < headers.size(); i++) {
			userRanks.addVertex(headers.get(i), Double.parseDouble(strResult.get(i)));
		}
		return userRanks;
	}
}
