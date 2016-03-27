package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.component.GitBarChart;

public class Test extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
//		List<Double> datas = new ArrayList<>();
//		datas.add(1.0);
//		datas.add(3.0);
//		datas.add(5.0);
//		datas.add(7.0);
//		datas.add(9.0);
//		List<String> headers = new ArrayList<>(Arrays.asList("a","b","c","d","e"));
//		GitPieChart pieChart = new GitPieChart(headers, datas, "testPieChart");
		
		List<String> headers = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g"));
		List<Number> numbers = new ArrayList<>(Arrays.asList(58,69.3,100,321.2,26,233,21.5));
		GitBarChart barChart = new GitBarChart(headers, numbers, "SName","TestBarChart", "xLabel", "yLabel");
		Scene scene = new Scene(barChart, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
