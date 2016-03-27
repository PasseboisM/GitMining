package chart_data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 各种雷达图数据的基类，其可能子类包括“仓库特征评价”等。<br/>
 * 
 * <pre>
 * 使用说明：
 *   <strong>逻辑层</strong>应该生成、提供的是本类的子类。为了避免逻辑层直接初始化此类，
 * 本类为abstract。
 *   子类应当屏蔽本类中的Vertex相关方法，替换为具体统计类型相关方法。
 *   
 *   <strong>界面层</strong>在调用对应逻辑层方法后，可以用本类（父类）的引用使用获得的对
 * 象，以使用统一的method signature.
 * </pre>
 * 
 * @author xjh14
 * Ver:
 * Created at: 2016年3月27日
 */
public abstract class RadarDatas {

		private List<RadarVertex> vertexes = new LinkedList<>();
		
		public void addVertex(String header, double mark) {
			vertexes.add(new RadarVertex(header, mark));
		}
		
		public int getNumOfVertexes() {
			return vertexes.size();
		}
		
		public Iterator<RadarVertex> getVertexes() {
			return vertexes.listIterator();
		}
		
		public class RadarVertex {
			public final String header;
			public final double mark;
			public RadarVertex(String header, double mark) {
				super();
				this.header = header;
				this.mark = mark;
			}
			
		}
}
