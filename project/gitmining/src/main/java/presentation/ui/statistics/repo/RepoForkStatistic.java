package presentation.ui.statistics.repo;

import java.io.IOException;

import chart_data.RepoDistOverFork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

/**
 * 项目贡献者数量分类的柱状图
 */

public class RepoForkStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				RepoForkStatistic.class.getResource("repositoryForkStatistic.fxml"));
		AnchorPane rootUINode = loader.load();
		RepoForkStatistic controller = loader.getController();
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
		GeneralStatisticsService generalStatisticsService = new GeneralStatisticsUtil();
		RepoDistOverFork repoDistOverFork = generalStatisticsService.getRepoDistOverFork();
		GitBarChart barChart = new GitBarChart(repoDistOverFork);
		anchorPane.getChildren().add(barChart);
	}
}
