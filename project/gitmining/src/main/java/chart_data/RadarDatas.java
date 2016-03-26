package chart_data;

import java.util.List;

public class RadarDatas {
		// TODO 之后添加其他方面的评分
		// 评分
		public List<Double> marks;

		// 文字描述列表
		public List<String> headers;

		public RadarDatas(List<Double> marks, List<String> headers) {
			this.marks = marks;
			this.headers = headers;
		}
}
