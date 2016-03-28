package presentation.ui.statistics.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.component.GitPieChart;

public class UserTypeStatisticsPane {

	public static AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserTypeStatisticsPane.class.getResource("userTypeStatistics.fxml"));
		AnchorPane rootUINode = loader.load();
		UserTypeStatisticsPane controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
//		this.rightComponentParent = rightComponentParent;
	}

	@FXML
	private AnchorPane anchorPane;
//	private AnchorPane rightComponentParent;

	public void initialChart() {
		List<Double> a = new ArrayList<>(Arrays.asList(1.0, 2.0));
		List<String> headers = new ArrayList<>(Arrays.asList("a", "b"));
		GitPieChart pieChart = new GitPieChart(headers, a, "PieUser");
		anchorPane.getChildren().add(pieChart);
	}
}
