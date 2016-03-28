package presentation.ui.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.GitPieChart;


public class UserStatistic_1Controller {

	
		public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
			FXMLLoader loader = new FXMLLoader(UserStatistic_1Controller.class.getResource("userStatistic.fxml"));
//			System.out.println("doing!");
			VBox rootUINode = loader.load();
			UserStatistic_1Controller controller = loader.getController();
			controller.initial(rightComponentParent);
//			System.out.println("done!");
			return rootUINode;
		}
		private void initial(AnchorPane rightComponentParent) {
			vMain.getChildren().add(initialChart());
			
			this.rightComponentParent=rightComponentParent;
			
			
		}
		
		
		@FXML
		private VBox vMain;
		
		private AnchorPane rightComponentParent;
		
		public ScrollPane initialChart(){
			

			List <Double> a=new ArrayList<>();
			a.add(1.0);
			a.add(2.0);
		
			List<String> headers = new ArrayList<>(Arrays.asList("a","b"));
			GitPieChart pieChart=new GitPieChart(headers,a,"PieUser");
			
			
			ScrollPane pane=new ScrollPane();
			VBox vBox=new VBox();
			vBox.setPrefWidth(1010);
			vBox.getChildren().add(pieChart);
		System.out.println(pieChart.toString());
//			vBox.getChildren().add(new Bar(null,null));

			pane.setContent(vBox);
		//	System.out.println("do something");
			
			return pane;
			
		}
}
