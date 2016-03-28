package chart_data.radar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Type: RepositoryStatistics<br />
 * 一个Repository的多项目评分统计数据（基于其数据在系统整体数据中水平进行评价）。
 * @author xjh14
 * Ver: 1.0 ---包含5条评价信息
 * Created at: 2016年3月27日
 */
public class RepositoryRanks extends RadarDatas {

	//TODO 本类内可以存放一些提示信息，比如应该生成的评分项目、预期项目数目，比如下面的List
	public static final List<String> defaultHeaders = Arrays.asList("forks",
			"open_issues", "size", "subscribers_count", "watchers");
	
	
	public void addVertex(String rankTitle, double rank) {
		super.addVertex(rankTitle, rank);
	}
	
	public int getNumOfVertexes() {
		return super.getNumOfVertexes();
	}
	
	public Iterator<RadarVertex> getVertexes() {
		return super.getVertexes();
	}
	
}
