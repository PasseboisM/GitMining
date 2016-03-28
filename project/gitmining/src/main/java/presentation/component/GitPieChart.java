package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;

public class GitPieChart extends PieChart {
	@FXML
	private PieChart pieChart;
	
	public GitPieChart(List<String> headers,List<Double> datas,String title) {
		FXMLLoader fxmlLoader = new FXMLLoader(GitPieChart.class.getResource("pieChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.initial(headers,datas,title);
	}


	private void initial(List<String> headers,List<Double> datas,String title) {

		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		Double sum = 0.0;
		for (Double Double : datas) {
			sum+=Double;
		}
		for (int i = 0; i < headers.size(); i++) {
			pieChartData.add(new PieChart.Data(headers.get(i), datas.get(i)*100.0/sum));
		}
		pieChart.setData(pieChartData);
		pieChart.setLegendSide(Side.RIGHT);
		for (PieChart.Data data : pieChart.getData()) {
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip(String.format("%.2f", data.getPieValue()) + "%");
			Tooltip.install(node, tooltip);
		}
		pieChart.setTitle(title);
	}

}
