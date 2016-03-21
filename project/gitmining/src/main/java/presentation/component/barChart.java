package presentation.component;

import java.io.IOException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class barChart extends AnchorPane {
	//
	private final static String language1 = "java";
	
	//建立一个柱状图，x轴为string类型，y轴为Integer类型
	private BarChart<String,Integer> barChar;
	
	//创建x轴的分类
	private CategoryAxis xAxis;
	
	//统计项目所使用语言的数据，横轴为语言类型
	private ObservableList<String> language = FXCollections.observableArrayList();
	
	private void initialize() {
        String[] x = {"x1","x2"};
        language.addAll(Arrays.asList(x));
        xAxis.setCategories(language);
    }

}
