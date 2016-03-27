package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class GitBarChart extends AnchorPane {
	@FXML
	private BarChart<String,Number> barChar;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	 final static String austria = "Austria";
	    final static String brazil = "Brazil";
	    final static String france = "France";
	    final static String italy = "Italy";
	    final static String usa = "USA";

	/**
	 * 柱状图构造函数
	 */
	public GitBarChart(List<String> columns, List<Number> values,String title,String xLabel,String yLabel) {
		FXMLLoader fxmlLoader = new FXMLLoader(GitBarChart.class.getResource("bar.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChar.setTitle(title);
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		this.initial();
	}
	

	/**
	 * 初始化柱状图
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initial() {
		XYChart.Series series1 = new XYChart.Series();
        series1.setName("test");       
        series1.getData().add(new XYChart.Data(austria,25601.34));
        series1.getData().add(new XYChart.Data(brazil,20148.82));
        series1.getData().add(new XYChart.Data(france,10000));
        series1.getData().add(new XYChart.Data(italy,35407.15));
        series1.getData().add(new XYChart.Data(usa,12000));  
        
        barChar.getData().add(series1);
    }

}
