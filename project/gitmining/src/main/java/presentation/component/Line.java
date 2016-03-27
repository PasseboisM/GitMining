package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private List<String> labels;
	
	//定义y轴上的数据double型
	private List<Double> datas;
	
	//折线图的标题
	private String title;
	
	public Line(List<String> labels,List<Double> datas,String title) {
		FXMLLoader fxmlLoader = new FXMLLoader(Line.class.getResource("line.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.datas = datas;
		this.initial(labels, datas, title);
	}
	/**
	 * 这个是主要调用的初始化接口，对外开放！
	 
	
	public Line(List<Double> datas,List<String> labels) {
		this(null, datas, title);
		this.setLabelsText(labels);
	}*/
	
	/**
	 * 初始化横轴上标签
	
	private void setLabelsText(List<String> languages2) {
		for(int i=0;i<labels.size();i++){
			labels.get(i).setText(languages2.get(i));
		}
	} */

	/**
	 * 初始化折线图
	 */
	private void initial(List<String> labels,List<Double> datas,String title) {
		// TODO Auto-generated method stub
		ObservableList<LineChart.Data> lineChartData = FXCollections.observableArrayList();
		Double sum = 0.0;
		for(int i=0;i<labels.size();i++){
			lineChartData.add(new LineChart.Data(labels.get(i),datas.get(i)));
		}
		line.setData(lineChartData);
		line.setTitle(title);
	}

}
