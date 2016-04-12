package chart_data.radar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class UserRanks extends RadarDatas {

	public static final List<String> defaultHeaders = Arrays.asList("followers",
			"following", "public_gists", "public_repos");
	public static final List<String> chineseHeaders = Arrays.asList("被关注",
			"关注", "分享", "项目");
	
	public void addVertex(String header, double userRank) {
		super.addVertex(header, userRank);
	}
	
	public int getNumOfVertexes() {
		return super.getNumOfVertexes();
	}
	
	public Iterator<RadarVertex> getVertexes() {
		return super.getVertexes();
	}
}
