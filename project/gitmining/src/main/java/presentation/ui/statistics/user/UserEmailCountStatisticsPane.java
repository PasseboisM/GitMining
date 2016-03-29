package presentation.ui.statistics.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

public class UserEmailCountStatisticsPane extends StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserEmailCountStatisticsPane.class.getResource("userEmailStatistics.fxml"));
		AnchorPane rootUINode = loader.load();
		UserEmailCountStatisticsPane controller = loader.getController();
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
		GitBarChart lineChart=new GitBarChart(headers,a,"用户","用户邮箱统计图","邮箱","用户个数");
		anchorPane.getChildren().add(lineChart);
	}
}
