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
	private BarChart<String,Number> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	

	/**
	 * 柱状图构造函数
	 */
	public GitBarChart(List<String> columns, List<Number> values,String seriesName,String title,String xLabel,String yLabel) {
		FXMLLoader fxmlLoader = new FXMLLoader(GitBarChart.class.getResource("barChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		barChart.setTitle(title);
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		this.initial(columns,values,seriesName);
	}
	

	/**
	 * 初始化柱状图
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initial(List<String> columns, List<Number> values, String seriesName) {
		XYChart.Series series = new XYChart.Series();
        series.setName(seriesName);
        for (int i = 0; i < columns.size(); i++) {
        	XYChart.Data data =new XYChart.Data(columns.get(i),values.get(i));
            series.getData().add(data);
        }
//        Node node = data.getNode();
//        Tooltip tooltip = new Tooltip(String.valueOf(999));
//        yAxis.get
//		Tooltip.install(xAxis, tooltip);
        barChart.getData().add(series);
        barChart.setCategoryGap(500/columns.size());

    }

}
