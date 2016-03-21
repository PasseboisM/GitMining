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
	private BarChart<String,Integer> barChart;
	private CategoryAxis xAxis;
	private ObservableList<String> xNames = FXCollections.observableArrayList();
	
	private void initialize() {
        String[] x = {"x1","x2"};
        xNames.addAll(Arrays.asList(x));
        xAxis.setCategories(xNames);
    }

}
