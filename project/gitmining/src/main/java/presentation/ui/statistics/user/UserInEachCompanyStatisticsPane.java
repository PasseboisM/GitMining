package presentation.ui.statistics.user;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import presentation.ui.statistics.StatisticsPane;

public class UserInEachCompanyStatisticsPane  implements StatisticsPane{

	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(UserInEachCompanyStatisticsPane.class.getResource("userInEachCompanyStatistics.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserInEachCompanyStatisticsPane controller = loader.getController();
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
//		List <Number> a = Arrays.asList(1.0,2.0,3.0,4.0,5.0);
//		List<String> headers = Arrays.asList("a","b","c","d","e");
//		GitBarChart barChart=new GitBarChart(headers,a,"用户","用户所在公司统计图","公司","用户个数");
//		anchorPane.getChildren().add(barChart);
	}
}
