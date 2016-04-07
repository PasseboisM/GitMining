package presentation.ui.statistics.user;

import java.io.IOException;

import chart_data.UserDistOverCreateTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitLineChart;
import presentation.ui.statistics.StatisticsPane;

public class UserCreateTimeStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserCreateTimeStatisticsPane.class.getResource("userCreateTimeStatistics.fxml"));
		AnchorPane rootUINode = loader.load();
		UserCreateTimeStatisticsPane controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}
	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
	}
	
	@FXML
	private AnchorPane anchorPane;
	
//	private AnchorPane rightComponentParent;
	
	public void initialChart(){
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		UserDistOverCreateTime userCreateOnTimeCounts = statisticsService.getUserDistOverCreateTime();
		GitLineChart lineChart = new GitLineChart(userCreateOnTimeCounts);
		anchorPane.getChildren().add(lineChart);
	}
}
