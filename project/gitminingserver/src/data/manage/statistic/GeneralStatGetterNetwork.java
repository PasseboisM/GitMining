package data.manage.statistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import data.service.stat.GeneralStatGetter;

public class GeneralStatGetterNetwork implements GeneralStatGetter {

	private static List<String> types = Arrays.asList(
					"UserDistOverFollower",
					"RepoDistOverFork",
					"RepoDistOverLanguage",
					"RepoDistOverCreateTime",
					"UserDistOverCreateTime",
					"RepoDistOverStar",
					"UserDistOverType"
				);
	private HashMap<String,String> staticStatistics = new HashMap<>(10);
	
	public GeneralStatGetterNetwork() {
		File staticFile = null;
		FileReader reader = null;
		BufferedReader bufferdReader = null;
		for (String type: types) {
			staticFile = new File("stat/"+type+".stat");
			try {
				reader = new FileReader(staticFile);
				bufferdReader = new BufferedReader(reader);
				staticStatistics.put(type, bufferdReader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getUserDistOverFollower() {
		return staticStatistics.get("UserDistOverFollower");
	}

	@Override
	public String getRepoDistOverFork() {
		return staticStatistics.get("RepoDistOverFork");
	}

	@Override
	public String getRepoDistOverLanguage() {
		return staticStatistics.get("RepoDistOverLanguage");
	}

	@Override
	public String getRepoDistOverCreateTime() {
		return staticStatistics.get("RepoDistOverCreateTime");
	}

	@Override
	public String getUserDistOverCreateTime() {
		return staticStatistics.get("UserDistOverCreateTime");
	}

	@Override
	public String getRepoDistOverStar() {
		return staticStatistics.get("RepoDistOverStar");
	}

	@Override
	public String getUserDistOverType() {
		return staticStatistics.get("UserDistOverType");
	}


}
