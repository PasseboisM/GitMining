package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Line extends AnchorPane {
	@FXML
	private LineChart line;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	//定义x轴上的label
	private List<Label> labels;
	
	//定义y轴上的数据double型
	private List<Double> datas;
	
	public Line(List<Double> datas) {
		FXMLLoader fxmlLoader = new FXMLLoader(Bar.class.getResource("line.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.initial();
		this.datas = datas;
	}

	private void initial() {
		// TODO Auto-generated method stub
		
	}

}
