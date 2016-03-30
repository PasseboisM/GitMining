package presentation.ui.statistics.user;

import java.io.IOException;

import chart_data.UserTypeCounts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitPieChart;
import presentation.ui.statistics.StatisticsPane;

public class UserTypeStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
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
//		List<Double> a = new ArrayList<>(Arrays.asList(1.0, 2.0));
//		List<String> headers = new ArrayList<>(Arrays.asList("a", "b"));
//		GitPieChart pieChart = new GitPieChart(headers, a, "用户类型统计图");
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		UserTypeCounts userTypeCounts = statisticsService.getUserTypeCounts();
		GitPieChart pieChart = new GitPieChart(userTypeCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
