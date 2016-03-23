package presentation.component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Bar extends AnchorPane {
	@FXML
	private BarChart barChar;
	@FXML
	private CategoryAxis xAxis;
	
	//横轴表示语言类型
	private List<Label> languages;
	
	//每种语言项目的数量
	private List<Integer> numOfRepo;
	
	//设置Bar的数量
	private Integer numOfBar;
	
	//设置label
	private List<Label> labels;
	
	/**
	 * 柱状图构造函数
	 * @param numOfRepo是每种语言的仓库数量
	 */
	public Bar(List<Integer> numOfRepo) {
		FXMLLoader fxmlLoader = new FXMLLoader(Bar.class.getResource("BarChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.numOfRepo = numOfRepo;
		this.numOfBar = numOfRepo.size();
		this.initial();
	}
	
	public Bar(List<Integer> numOfRepo,List<String> languages) {
		this(numOfRepo);
		this.setLabelsText(languages);
	}
	/**
	 * 初始化横轴上标签
	 */
	private void setLabelsText(List<String> languages2) {
		for(int i=0;i<numOfBar;i++){
			labels.get(i).setText(languages2.get(i));
		}
	}

	/**
	 * 初始化柱状图
	 */
	private void initial() {
        
    }

}
