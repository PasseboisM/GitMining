package logic.calc.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import chart_data.UserDistOverFollower;
import chart_data.RepoDistOverFork;
import chart_data.RepoDistOverLanguage;
import chart_data.RepoDistOverCreateTime;
import chart_data.RepoDistOverStar;
import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import chart_data.service.GeneralStatisticsService;
import chart_data.UserDistOverType;
import chart_data.UserDistOverType.UserTypeCount;
import common.enumeration.attribute.Language;
import logic.calc.python.PythonRunner;

public class GeneralStatisticsUtil implements GeneralStatisticsService{

	private Gson gson = new Gson();
	
	@Override
	public UserDistOverFollower getUserDistOverFollower() {
		String pyFile = "user_dist_over_follower.py";
		
		/*
		 * 手动统计了一下，在27000多条数据中，8人超过10000,175人超过1000，有24000+人不到100
		 * 最大值18727
		 * 于是不能采用均一的区间长度
		 */
		
		List<Integer> gaps = new ArrayList<>(20);
		for(int i=0;i<10;i++) gaps.add(10);//0~100  10份
		for(int i=0;i<5;i++) gaps.add(20);//100~200  5份
		for(int i=0;i<2;i++) gaps.add(100);//200~400  2份
		for(int i=0;i<2;i++) gaps.add(300);//400~1000  2份
		for(int i=0;i<2;i++) gaps.add(4500);//1000~10000 2份
		gaps.add(10000);//10000~20000  1份     总计22份
		
		String gapsJSON = gson.toJson(gaps);
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, gapsJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new UserDistOverFollower();
		}
		
		UserDistOverFollower followerNumberRanges = new UserDistOverFollower();			

		int lower = 0;
		for(int i=0;i<gaps.size();i++) {
			followerNumberRanges.addNewRange(lower, lower+gaps.get(i),
					Integer.parseInt(result.get(i)));
			lower += gaps.get(i);
		}
		
		return followerNumberRanges;
	}

	@Override
	public RepoDistOverFork getRepoDistOverFork() {
		String pyFile = "repo_dist_over_fork.py";
		
		/*
		 * 最高9000；72个大于1000的，800个大于100的；2000多个不到100的
		 */
		List<Integer> gaps = new ArrayList<>(20);
//		for(int i=0;i<10;i++) gaps.add(10);
//		for(int i=0;i<9;i++) gaps.add(100);
//		for(int i=0;i<3;i++) gaps.add(3000);
		gaps.add(40);
		for(int i=0;i<6;i++) gaps.add(60);
		for(int i=0;i<5;i++) gaps.add(200);
		for(int i=0;i<1;i++) gaps.add(7760);
		
		String gapsJSON = gson.toJson(gaps);
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, gapsJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new RepoDistOverFork();
		}
		
		RepoDistOverFork repoDistOverFork = new RepoDistOverFork();
		
		int lower = 0;
		for(int i=0;i<gaps.size();i++) {
			repoDistOverFork.addRange(lower, lower+gaps.get(i),
					Integer.parseInt(result.get(i)));
			lower += gaps.get(i);
		}

		return repoDistOverFork;
	}

	@Override
	public RepoDistOverLanguage getRepoDistOverLanguage() {
		String pyFile = "repo_dist_over_lang.py";
		
		ArrayList<String> langList = new ArrayList<String>();
		for(Language l:Language.values()) {
			langList.add(l.getName());
		}
		langList.remove(Language.ALL.getName());
		langList.remove(Language.OTHERS.getName());
		
		String langJSON = gson.toJson(langList);

		List<String> result = null;
		
		try {
			result = PythonRunner.runPython(pyFile, langJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new RepoDistOverLanguage();
		}
		
		RepoDistOverLanguage languageCounts = new RepoDistOverLanguage();
		int count = 0;
		for(String lang:langList) {
			languageCounts.addLanguageCount(Language.getLanguage(lang),
					Integer.parseInt(result.get(count)));
			count ++;
		}
		
		
		return languageCounts;
	}

	@Override
	public RepoDistOverCreateTime getRepoDistOverCreateTime() {
		String pyFile = "repo_dist_over_create_time.py";
		
		/*
		 * 初步统计：最早为2007-10;最晚至2010-08
		 * 决定4个月算一次间隔
		 */
		List<String> divList = Arrays.asList(
				"2007-10","2008-02","2008-06","2008-10","2009-02","2009-06",
				"2009-10","2010-02","2010-06","2010-10");
		String divJSON = gson.toJson(divList);
		
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, divJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new RepoDistOverCreateTime();
		}
		
		RepoDistOverCreateTime repoCreateOnTimeCounts = new RepoDistOverCreateTime();

		for(int i=0;i<divList.size()-1;i++) {
			repoCreateOnTimeCounts.addCreateCount(
					divList.get(i),divList.get(i+1),
					Integer.parseInt(result.get(i)));
		}
		
		return repoCreateOnTimeCounts;
	}

	@Override
	public UserDistOverCreateTime getUserDistOverCreateTime() {
		String pyFile = "user_dist_over_create_time.py";
		
		/*
		 * 包括2007-2015的数据，故按年分
		 */
		ArrayList<String> divList = new ArrayList<>();
		for(int i=2007;i<=2015;i++) {
			divList.add(""+i);
		}
		
		String divJSON = gson.toJson(divList);
		
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, divJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new UserDistOverCreateTime();
		}
		
		UserDistOverCreateTime userCreateOnTimeCounts = new UserDistOverCreateTime();

		for(int i=0;i<divList.size()-1;i++) {
			userCreateOnTimeCounts.addCreateCount(
					divList.get(i), divList.get(i+1), 
					Integer.parseInt(result.get(i)));
		}
		
		return userCreateOnTimeCounts;
	}

	@Override
	public RepoDistOverStar getRepoDistOverStar() {
		String pyFile = "repo_dist_over_star.py";
		
		/*
		 * 初步统计1200个不到100的；1300个100至1000的；500个1000+的（其中13个上万的）
		 * 最高为37000
		 */
		List<Integer> gaps = new ArrayList<>();
		gaps.add(40);//0--40 1份
		gaps.add(60);//40--100 1份
		for(int i=0;i<2;i++) gaps.add(80);//100--260 2份
		gaps.add(100);//260--360 1份
		gaps.add(120);//360--480 1份
		gaps.add(150);//480--6300 1份
		gaps.add(200);//630--830 1份
		gaps.add(250);//830--1080 1份
		gaps.add(300);//1080--1380 1份
		gaps.add(400);//1380--1780 1份
		gaps.add(800);//1780--2580 1份
		gaps.add(1000);//2580--3580 1份
		gaps.add(2000);//3580--5580 1份
		gaps.add(3000);//5580--8580 1份
		gaps.add(30000);
		
		String gapsJSON = gson.toJson(gaps);
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, gapsJSON);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new RepoDistOverStar();
		}
		
		RepoDistOverStar repoDistOverStar = new RepoDistOverStar();
		
		int lower = 0;
		for(int i=0;i<gaps.size();i++) {
			repoDistOverStar.addRange(lower, lower+gaps.get(i), 
					Integer.parseInt(result.get(i)));
			lower += gaps.get(i);
		}
		
		return repoDistOverStar;
	}

	@Override
	public UserDistOverType getUserDistOverType() {
		String pyFile = "user_dist_over_type.py";

		List<String> headers = UserDistOverType.headers;
		String param1 = gson.toJson(headers);
		
		List<String> result = null;
		try {
			result = PythonRunner.runPython(pyFile, param1);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return new UserDistOverType();
		}
		
		UserDistOverType userTypeCounts = new UserDistOverType();
		
		int index = 0;
		for(String head:headers) {
			userTypeCounts.addCount(head, Integer.parseInt(result.get(index++)));
		}

		return userTypeCounts;
		
	}

	public static void main(String[] args) {
		GeneralStatisticsUtil util = new GeneralStatisticsUtil();
		String s = new Gson().toJson(util.getRepoDistOverLanguage());
		System.out.println(s);
	}
	
}
