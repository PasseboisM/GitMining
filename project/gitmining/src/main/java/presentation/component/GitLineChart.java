package presentation.component;

import java.io.IOException;
import java.util.List;

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
	private CategoryAxis xAxis ;
	@FXML
	private NumberAxis yAxis ;
	
/**
 * 折线图构造函数
 */	
	public GitLineChart(List<String> labels,List<Number> datas,String seriesName,String title,String xLabel,String yLabel) {
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
		this.initial(labels, datas, seriesName);
	}

	/**
	 * 初始化折线图
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initial(List<String> labels,List<Number> datas,String seriesName) {
        XYChart.Series series = new XYChart.Series();
        series.setName(seriesName);
        for (int i = 0; i < labels.size(); i++) {
        	series.getData().add(new XYChart.Data(labels.get(i), datas.get(i)));
		}
        lineChart.getData().add(series);
        for (int i = 0; i < labels.size(); i++) {
			XYChart.Data data = (Data) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip(String.valueOf(datas.get(i)));
			Tooltip.install(node, tooltip);
		}
	}

}
