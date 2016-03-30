package presentation.ui.statistics.repo;

import java.io.IOException;

import chart_data.RepoCreateOnTimeCounts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
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
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		RepoCreateOnTimeCounts repoCreateOnTimeCounts = statisticsService.getRepoCreateOnTimeCounts();
		GitPieChart pieChart = new GitPieChart(repoCreateOnTimeCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
