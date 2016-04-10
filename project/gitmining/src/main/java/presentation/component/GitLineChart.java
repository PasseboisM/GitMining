package presentation.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import chart_data.UserDistOverFollower;
import chart_data.UserDistOverFollower.FollowerNumberRange;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class GitLineChart extends AnchorPane {
	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	/**
	 * 折线图构造函数
	 */
	public GitLineChart(List<String> labels, List<Number> datas, String seriesName, String title, String xLabel,
			String yLabel) {
		this.initialFXML();
		this.initialText(title, xLabel, yLabel);
		this.initial(labels, datas, seriesName);
	}
	
	public GitLineChart(UserDistOverFollower followerNumberRanges) {
		this.initialFXML();
		this.initialText("被关注数统计图", "用户","被关注数");
		this.initial(followerNumberRanges,"用户");
	}
	
	public GitLineChart(UserDistOverCreateTime userDistOverCreateTime){
		this.initialFXML();
		this.initialText("用户创建时间统计图", "创建时间", "用户个数");
		this.initial(userDistOverCreateTime, "用户");
	}


	private void initialText(String title, String xLabel, String yLabel) {
		lineChart.setTitle(title);
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
	}

	private void initialFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(GitLineChart.class.getResource("lineChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化折线图
	 */
	private void initial(List<String> labels, List<Number> datas, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		for (int i = 0; i < labels.size(); i++) {
			series.getData().add(new XYChart.Data<String,Number>(labels.get(i), datas.get(i)));
		}
		lineChart.getData().add(series);
		for (int i = 0; i < labels.size(); i++) {
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip(String.valueOf(datas.get(i)));
			Tooltip.install(node, tooltip);
		}
	}
	//TODO 可能要改一下chart_data数据包类
	private void initial(UserDistOverFollower followerNumberRanges, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
//		int numOfFollower = 0;
		Iterator<FollowerNumberRange> iterator = followerNumberRanges.getUserRanges();
		while (iterator.hasNext()) {
			FollowerNumberRange followerNumberRange = iterator.next();
			int lowerRange = followerNumberRange.lowerRange;
			int higherRange = followerNumberRange.higherRange;
			int numOfFollower = followerNumberRange.numOfUsers;
			series.getData().add(new XYChart.Data<String,Number>(lowerRange+"-"+higherRange, numOfFollower));
		}
		lineChart.getData().add(series);
		iterator = followerNumberRanges.getUserRanges();
		for (int i = 0; i <followerNumberRanges.getNumOfRange(); i++) {
			FollowerNumberRange followerNumberRange = iterator.next();
			int numOfFollower = followerNumberRange.numOfUsers;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("用户数量："+numOfFollower+"个");
			Tooltip.install(node, tooltip);
		}
	}
	private void initial(UserDistOverCreateTime userDistOverCreateTime, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<UserCreateOnTimeCount> iterator = userDistOverCreateTime.getCounts();
		while (iterator.hasNext()) {
			UserCreateOnTimeCount followerNumberRange = iterator.next();
			String lowerRange = followerNumberRange.timeLo;
			String higherRange = followerNumberRange.timeHi;
			int numOfFollower = followerNumberRange.count;
			series.getData().add(new XYChart.Data<String,Number>(lowerRange+"~"+higherRange, numOfFollower));
		}
		lineChart.getData().add(series);
		iterator = userDistOverCreateTime.getCounts();
		for (int i = 0; i <userDistOverCreateTime.getNumOfCount(); i++) {
			UserCreateOnTimeCount followerNumberRange = iterator.next();
			int numOfFollower = followerNumberRange.count;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("用户数量："+numOfFollower+"个");
			Tooltip.install(node, tooltip);
		}
	}

}
