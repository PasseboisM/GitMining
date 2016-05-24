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
	
	public GeneralStatGetterNetwork() throws IOException {
		File staticFile = null;
		FileReader reader = null;
		BufferedReader bufferdReader = null;
		for (String type: types) {
			staticFile = new File("stat/"+type+".stat");
			reader = new FileReader(staticFile);
			bufferdReader = new BufferedReader(reader);
			staticStatistics.put(type, bufferdReader.readLine());
			System.out.println("Got:"+type+"  "+staticStatistics.get(type));
		}
	}

	@Override
	public String getUserDistOverFollower() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepoDistOverFork() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepoDistOverLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepoDistOverCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserDistOverCreateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepoDistOverStar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserDistOverType() {
		// TODO Auto-generated method stub
		return null;
	}


}
