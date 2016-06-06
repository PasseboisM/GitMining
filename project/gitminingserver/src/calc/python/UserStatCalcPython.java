package calc.python;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import calc.python.core.PythonRunner;
import calc.service.UserStatService;
import chart_data.radar.UserRanks;
import common.service.GitUser;


public class UserStatCalcPython implements UserStatService {

	private static final Gson gson = new Gson();
	private static final String SINGLE_USER_FILE =  "user_single_rank.py";
	
	/**
	 * Dependency: statistic_single_repo_rank.py
	 * 	Python input: 
	 * 		1. Headers as JSON list
	 * 		2. GitUser data in JSON format
	 * 	Python output:
	 * 		Rank results in the corresponding order as the header list.
	 */
	@Override
	public UserRanks getRanks(GitUser u) {
		List<String> headers = UserRanks.defaultHeaders;
		List<String> chHeaders = UserRanks.chineseHeaders;
		String headerStr = gson.toJson(headers);
		String userStr = gson.toJson(u);
		
		List<String> strResult = null;
		try {
			strResult = PythonRunner.runPython(SINGLE_USER_FILE, headerStr, userStr);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		UserRanks userRanks = new UserRanks();

		for (int i = 0; i < headers.size(); i++) {
			userRanks.addVertex(chHeaders.get(i), Double.parseDouble(strResult.get(i)));
		}
		return userRanks;
	}

}
