package presentation.ui.statistics.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.component.GitPieChart;
import presentation.ui.statistics.StatisticsPane;

public class RepoCreateTimeStatistic implements StatisticsPane {

	/**
	 * 仓库创建时间相关的饼图
	 */

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				RepoCreateTimeStatistic.class.getResource("repositoryCreateTimeStatistic.fxml"));
		AnchorPane rootUINode = loader.load();
		RepoCreateTimeStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
		// this.rightComponentParent=rightComponentParent;
	}

	@FXML
	private AnchorPane anchorPane;

	// private AnchorPane rightComponentParent;

	public void initialChart() {
		List<Double> a = new ArrayList<>(Arrays.asList(1.0, 2.0));
		List<String> headers = new ArrayList<>(Arrays.asList("a", "b"));
		GitPieChart pieChart = new GitPieChart(headers, a, "项目创建时间统计图");
		anchorPane.getChildren().add(pieChart);
	}
}
