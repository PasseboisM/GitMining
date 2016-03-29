package presentation.ui.statistics.repo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

/**
 * 项目贡献者数量分类的柱状图
 */

public class RepoContributorStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				RepoContributorStatistic.class.getResource("repositoryContributorStatistic.fxml"));
		AnchorPane rootUINode = loader.load();
		RepoContributorStatistic controller = loader.getController();
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
		List<Number> a = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		List<String> headers = Arrays.asList("a", "b", "c", "d", "e");
		GitBarChart barChart = new GitBarChart(headers, a, "项目", "项目贡献者统计图", "范围", "项目个数");
		anchorPane.getChildren().add(barChart);
	}
}
