package presentation.component;

import java.io.IOException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;

import javafx.application.Platform;
import javafx.fxml.FXML;
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
	

	
	
	final CategoryAxis xAxis2 = new CategoryAxis();
	final NumberAxis yAxis2 =new NumberAxis(0,60000,5000);
	final NumberAxis yAxis =new NumberAxis(0,12000,1000);
	BarChart barChart =new BarChart(xAxis2,yAxis);
	LineChart lineChart =new LineChart(xAxis2,yAxis2);
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
		xAxis2.setLabel(xLabel);
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
		
		AnchorPane.setBottomAnchor(pane, (double) 20);
		AnchorPane.setTopAnchor(pane, (double) 40);
		AnchorPane.setLeftAnchor(pane, (double) 30);
		AnchorPane.setRightAnchor(pane,(double) 30);
		barChart.setPrefHeight(600);
		barChart.setPrefWidth(1000);
		barChart.setHorizontalGridLinesVisible(false);
		barChart.setVerticalGridLinesVisible(false);
		//barChart.setHorizontalZeroLineVisible(false);
		barChart.setBarGap(34);
		barChart.getYAxis().setSide(Side.RIGHT);
		lineChart.setPrefHeight(600);
		lineChart.setPrefWidth(1000);
		lineChart.setHorizontalGridLinesVisible(false);
		lineChart.setVerticalGridLinesVisible(false);
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
		List<Number> datasLine=new LinkedList();  
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
	//TODO 可能要改一下chart_data数据包类

	private void initial(UserDistOverCreateTime userDistOverCreateTime, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		XYChart.Series<String,Number> series_2 = new XYChart.Series<>();
		series.setName(seriesName);
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
