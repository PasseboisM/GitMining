package presentation.ui.statistics.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

public class UserLocationCountStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserLocationCountStatisticsPane.class.getResource("userLocationStatistics.fxml"));
		AnchorPane rootUINode = loader.load();
		UserLocationCountStatisticsPane controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}
	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
//		this.rightComponentParent=rightComponentParent;
	}
	
	
	@FXML
	private AnchorPane anchorPane;
//	private AnchorPane rightComponentParent;
	public void initialChart(){
		List <Number> a = Arrays.asList(1.0,2.0,3.0,4.0,5.0);
		List<String> headers = Arrays.asList("a","b","c","d","e");
		GitBarChart barChart=new GitBarChart(headers,a,"用户","用户所在地统计图","所在地","用户个数");
		anchorPane.getChildren().add(barChart);
	}
}
