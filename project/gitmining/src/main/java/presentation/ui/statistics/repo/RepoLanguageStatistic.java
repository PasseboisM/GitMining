package presentation.ui.statistics.repo;

import java.io.IOException;

import chart_data.RepoDistOverLanguage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitPieChart;
import presentation.ui.statistics.StatisticsPane;

/**
 * 项目语言分类的统计的柱状图
 */

public class RepoLanguageStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(RepoLanguageStatistic.class.getResource("repositoryLanguageStatistic.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RepoLanguageStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
	}

	@FXML
	private AnchorPane anchorPane;

	public void initialChart() {
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		RepoDistOverLanguage languageCounts = statisticsService.getRepoDistOverLanguage();
		GitPieChart pieChart = new GitPieChart(languageCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
