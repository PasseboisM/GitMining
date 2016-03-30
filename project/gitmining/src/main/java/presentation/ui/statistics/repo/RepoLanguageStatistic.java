package presentation.ui.statistics.repo;

import java.io.IOException;

import chart_data.LanguageCounts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

/**
 * 项目语言分类的统计的柱状图
 */

public class RepoLanguageStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoLanguageStatistic.class.getResource("repositoryLanguageStatistic.fxml"));
		AnchorPane rootUINode = loader.load();
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
		LanguageCounts languageCounts = statisticsService.getLanguageCounts();
		GitBarChart barChart = new GitBarChart(languageCounts);
//		List<Number> a = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
//		List<String> headers = Arrays.asList("a", "b", "c", "d", "e");
//		GitBarChart barChart = new GitBarChart(headers, a, "项目", "项目语言统计图", "语言", "项目个数");
		anchorPane.getChildren().add(barChart);
	}
}
