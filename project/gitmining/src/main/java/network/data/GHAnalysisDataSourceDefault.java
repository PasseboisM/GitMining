package network.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import common.service.Repository;
import network.api.AnalysisApiMakerGitMining;
import network.api.service.AnalysisApiMaker;
import network.connection.service.HTTPConnectionService;

public class GHAnalysisDataSourceDefault {
	
	private AnalysisApiMaker analysisApi = null;
	private HTTPConnectionService conn = null;
	
	final static String REPO_PATTERN = "<li.*?prefix\">(.*?)</span>.*?slash\">/</span>(.*?)</a>.*?</li>";
	
	public List<Repository> recommendRepositories(Language language) throws NetworkException, IOException{
		String url = analysisApi.makeRepositoryTrendingApi(language);
		String html = conn.do_get(url);
		Pattern pattern = Pattern.compile(REPO_PATTERN, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(html);
		/*BufferedWriter bw = new BufferedWriter(new FileWriter("trending.html"));
		bw.write(html);*/
		while(matcher.find()){ 
            System.out.println(matcher.group(1)+"/"+matcher.group(2).trim()); 
        } 
		return null;
	}
	
	public GHAnalysisDataSourceDefault() {
//		ApiMakerService apiMaker = ApiMakerService.getInstance();
		this.analysisApi = new AnalysisApiMakerGitMining();
		this.conn = HTTPConnectionService.getInstance();
	}
}
