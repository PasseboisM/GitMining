package presentation.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class GitMixChart extends AnchorPane {
	

	
//	final CategoryAxis xAxis = new CategoryAxis();
	final CategoryAxis xAxis2 = new CategoryAxis();
	final NumberAxis yAxis2 =new NumberAxis();
	final NumberAxis yAxis =new NumberAxis();
	BarChart<String,Number> barChart =new BarChart<String,Number>(xAxis2,yAxis);
	LineChart<String,Number> lineChart =new LineChart<String,Number>(xAxis2,yAxis2);
	/**
	 * 折线图构造函数
	 */
	public GitMixChart(List<String> labels, List<Number> datasBar, String seriesName, String title, String xLabel,
			String yLabel) {
		this.initialFXML();
		this.initialText(title, xLabel, yLabel);
		this.initial(labels, datasBar, seriesName);
	}
	

	
	public GitMixChart(UserDistOverCreateTime userDistOverCreateTime){
		this.initialFXML();
		this.initialText("用户创建时间统计图", "创建时间", "用户个数");
		this.initial(userDistOverCreateTime, "用户");
	}


	private void initialText(String title, String xLabel, String yLabel) {
		lineChart.setTitle(title);
//		xAxis.setLabel(xLabel);
		xAxis2.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		yAxis2.setLabel(yLabel);
	}

	private void initialFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(GitLineChart.class.getResource("mixChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		AnchorPane pane=null;
		try {
			 pane = (AnchorPane) fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AnchorPane.setBottomAnchor(pane, 20.0);
		AnchorPane.setTopAnchor(pane, 20.0);
		AnchorPane.setLeftAnchor(pane, 20.0);
		AnchorPane.setRightAnchor(pane, 20.0);
		barChart.prefHeightProperty().bind(pane.heightProperty());
		barChart.prefWidthProperty().bind(pane.widthProperty());
		barChart.setHorizontalGridLinesVisible(false);
		barChart.setVerticalGridLinesVisible(false);
		barChart.getYAxis().setSide(Side.RIGHT);
		lineChart.prefHeightProperty().bind(pane.heightProperty());
		lineChart.prefWidthProperty().bind(pane.widthProperty());
		lineChart.setHorizontalGridLinesVisible(false);
		lineChart.setVerticalGridLinesVisible(false);
//		xAxis.maxWidthProperty().bind(pane.widthProperty());
//		xAxis.minWidthProperty().bind(pane.widthProperty());
//		xAxis2.maxWidthProperty().bind(pane.widthProperty());
//		xAxis2.minWidthProperty().bind(pane.widthProperty());
		pane.getChildren().add(barChart);
		pane.getChildren().add(lineChart);
	}

	/**
	 * 初始化帕累托图
	 */
	private void initial(List<String> labels, List<Number> datasBar,String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		XYChart.Series<String,Number> series_2 = new XYChart.Series<>();
		series.setName(seriesName);
		List<Number> datasLine=new LinkedList<>();  
		datasLine.add(datasBar.get(0));
		for(int i= 1;i<labels.size();i++){
			datasLine.add(datasLine.get(i-1).intValue()+datasBar.get(i).intValue());
		}
		for (int i = 0; i < labels.size(); i++) {
			series.getData().add(new XYChart.Data<String,Number>(labels.get(i), datasBar.get(i)));
			series_2.getData().add(new XYChart.Data<String,Number>(labels.get(i), datasLine.get(i)));
		}
		lineChart.getData().add(series_2);
		barChart.getData().add(series);
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < labels.size(); i++) {
					XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
					Node node = data.getNode();
					Tooltip tooltip = new Tooltip(String.valueOf(datasBar.get(i)));
					Tooltip.install(node, tooltip);
				}
			}
		});
	}
	

	private void initial(UserDistOverCreateTime userDistOverCreateTime, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		XYChart.Series<String,Number> series_2 = new XYChart.Series<>();
		series.setName(seriesName);
		series_2.setName(seriesName);
		Iterator<UserCreateOnTimeCount> iterator = userDistOverCreateTime.getCounts();
		int sumFollower=0;
		while (iterator.hasNext()) {
			UserCreateOnTimeCount followerNumberRange = iterator.next();
			String lowerRange = followerNumberRange.timeLo;
			String higherRange = followerNumberRange.timeHi;
			int numOfFollower = followerNumberRange.count;
			sumFollower=sumFollower+numOfFollower;
			series.getData().add(new XYChart.Data<String,Number>(lowerRange+"~"+higherRange, numOfFollower));
			series_2.getData().add(new XYChart.Data<String,Number>(lowerRange+"~"+higherRange, sumFollower));
		}
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				lineChart.getData().add(series_2);
				barChart.getData().add(series);
				barChart.setCategoryGap(500.0 / userDistOverCreateTime.getNumOfCount());
				Iterator<UserCreateOnTimeCount> runLaterIterator = userDistOverCreateTime.getCounts();
				for (int i = 0; i <userDistOverCreateTime.getNumOfCount(); i++) {
					UserCreateOnTimeCount followerNumberRange = runLaterIterator.next();
					int numOfFollower = followerNumberRange.count;
					XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
					Node node = data.getNode();
					Tooltip tooltip = new Tooltip("用户数量："+numOfFollower+"个");
					Tooltip.install(node, tooltip);
				}
			}
		});
	}

}
