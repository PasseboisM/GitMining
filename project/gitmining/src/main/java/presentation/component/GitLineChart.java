package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class GitLineChart extends AnchorPane {
	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private CategoryAxis xAxis ;
	@FXML
	private NumberAxis yAxis ;
	
/**
 * 折线图构造函数
 */	
	public GitLineChart(List<Double> labels,List<Double> datas,String title,String xLabel,String yLabel) {
		FXMLLoader fxmlLoader = new FXMLLoader(GitLineChart.class.getResource("lineChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lineChart.setTitle(title);
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		this.initial();
	}

	/**
	 * 初始化折线图
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initial() {
        XYChart.Series series = new XYChart.Series();
        series.setName("testLine");
		series.getData().add(new XYChart.Data(1.0+"", 23.0));
        series.getData().add(new XYChart.Data(2.0+"", 14.0));
        series.getData().add(new XYChart.Data(3.0+"", 15.0));
        series.getData().add(new XYChart.Data(4.0+"", 24.0));
        series.getData().add(new XYChart.Data(5.0+"", 34.0));
        
        lineChart.getData().add(series);
	}

}
