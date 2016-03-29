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
		List<Number> a = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
		List<String> headers = Arrays.asList("a", "b", "c", "d", "e");
		GitBarChart barChart = new GitBarChart(headers, a, "用户", "用户博客统计图", "博客名", "用户个数");
		anchorPane.getChildren().add(barChart);
	}
}
