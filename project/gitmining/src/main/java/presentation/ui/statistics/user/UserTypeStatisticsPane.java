package presentation.ui.statistics.user;

import java.io.IOException;

import chart_data.UserDistOverType;
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
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		UserDistOverType userTypeCounts = statisticsService.getUserDistOverType();
		GitPieChart pieChart = new GitPieChart(userTypeCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
