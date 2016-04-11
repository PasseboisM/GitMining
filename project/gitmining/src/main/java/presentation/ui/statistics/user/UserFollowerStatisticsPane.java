package presentation.ui.statistics.user;

import java.io.IOException;

import chart_data.UserDistOverFollower;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitLineChart;
import presentation.ui.statistics.StatisticsPane;

public class UserFollowerStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(UserFollowerStatisticsPane.class.getResource("userFollowerStatistics.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserFollowerStatisticsPane controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
	}

	@FXML
	private AnchorPane anchorPane;

	public void initialChart() {
		GeneralStatisticsService generalStatisticsService = new GeneralStatisticsUtil();
		UserDistOverFollower followerNumberRanges = generalStatisticsService.getUserDistOverFollower();
		GitLineChart lineChart = new GitLineChart(followerNumberRanges);
		anchorPane.getChildren().add(lineChart);
	}
}
