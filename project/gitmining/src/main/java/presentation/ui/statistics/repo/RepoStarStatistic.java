package presentation.ui.statistics.repo;

import java.io.IOException;

import chart_data.RepoDistOverStar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

/**
 * 统计项目合作者的柱状图
 */

public class RepoStarStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(
				RepoStarStatistic.class.getResource("repositoryStarStatistic.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RepoStarStatistic controller = loader.getController();
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
		RepoDistOverStar repoDistOverStar = generalStatisticsService.getRepoDistOverStar();
		GitBarChart barChart = new GitBarChart(repoDistOverStar);
		anchorPane.getChildren().add(barChart);
	}
}
