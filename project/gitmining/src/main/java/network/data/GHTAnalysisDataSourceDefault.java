package network.data;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import network.api.service.AnalysisApiMaker;
import network.api.service.ApiMakerService;
import network.connection.service.HTTPConnectionService;
import network.service.GHTAnalysisDataSource;

public class GHTAnalysisDataSourceDefault implements GHTAnalysisDataSource{
	
	private AnalysisApiMaker analysisApi = null;
	private HTTPConnectionService conn = null;
	
	private final static String REPO_RE = "<li.*?prefix\">(.*?)</span>.*?slash\">/</span>(.*?)</a>.*?</li>";
	private final static String USER_RE = "user-leaderboard-list-name\">.*?<a.*?>(.*?)(<span.*?/span>.*?)?</a>";
	
	public List<String> recommendRepositories(Language language) throws NetworkException{
		String url = analysisApi.makeRepositoryTrendingApi(language);
		String html = conn.do_get(url);
		return findRepoMatchItems(html);
	}
	
	public List<String> recommendUsers(Language language) throws NetworkException{
		String url = analysisApi.makeUserTrendingApi(language);
		String html = conn.do_get(url);
		return findUserMatchItems(html);
	}

	private List<String> findRepoMatchItems(String html) {
		Pattern pattern = Pattern.compile(REPO_RE, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		List<String> result = new ArrayList<>();
		while(matcher.find()){
            String s = matcher.group(1).trim()+"/"+matcher.group(2).trim();
            result.add(s);
        }
		return result;
	}
	
	private List<String> findUserMatchItems(String html) {
		Pattern pattern = Pattern.compile(USER_RE, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		List<String> result = new ArrayList<>();
		while(matcher.find()){
            String s = matcher.group(1).trim();
            result.add(s);
        }
		return result;
	}
	
	
	
	public GHTAnalysisDataSourceDefault() {
		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.analysisApi = apiMaker.getAnalysisApiMaker();
		this.conn = HTTPConnectionService.getInstance();
	}
}
