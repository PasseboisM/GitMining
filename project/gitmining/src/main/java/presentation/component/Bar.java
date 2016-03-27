package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Bar extends AnchorPane {
	@FXML
	private BarChart barChar;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	//横轴表示类型
	private List<Label> labels;
	
	//每种语言项目的数量
	private List<Integer> numOfRepo;
	
	
	
	/**
	 * 柱状图构造函数
	 * @param numOfRepo是每种语言的数量
	 */
	public Bar(List<Integer> numOfRepo) {
		FXMLLoader fxmlLoader = new FXMLLoader(Bar.class.getResource("bar.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.numOfRepo = numOfRepo;
		
		this.initial();
	}
	

	/**
	 * 这个是主要调用的初始化接口，对外开放！
	 */
	
	public Bar(List<Integer> numOfRepo,List<String> labels) {
		this(numOfRepo);
		this.setLabelsText(labels);
	}
	
	
	
	/**
	 * 初始化横轴上标签
	 */
	private void setLabelsText(List<String> languages2) {
		for(int i=0;i<numOfRepo.size();i++){
			labels.get(i).setText(languages2.get(i));
		}
	}

	/**
	 * 初始化柱状图
	 */
	private void initial() {
        
    }

}
