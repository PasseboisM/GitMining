package presentation.ui.statistics.repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.GitBarChart;
import presentation.ui.statistics.StatisticsPane;

/*统计项目规模的柱状图
 * */



public class RepoCollaboratorStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoCollaboratorStatistic.class.getResource("repositoryCollaboratorStatistic.fxml"));
//		System.out.println("doing!");
		AnchorPane rootUINode = loader.load();
		RepoCollaboratorStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
//		System.out.println("done!");
		return rootUINode;
	}
	private void initial(AnchorPane rightComponentParent) {
		vMain.getChildren().add(initialChart());
		
//		this.rightComponentParent=rightComponentParent;
		
		
	}
	
	
	@FXML
	private VBox vMain;
	
//	private AnchorPane rightComponentParent;
	
	public ScrollPane initialChart(){
	
		
		
		List <Number> a=new ArrayList<>();
		a.add(1.0);
		a.add(2.0);
		a.add(3.0);
		a.add(4.0);
		a.add(5.0);
		List<String> headers = new ArrayList<>(Arrays.asList("a","b","c","d","e"));
		GitBarChart barChart=new GitBarChart(headers,a,"a1","a2","a3","a4");
		
		
		
		
		
		ScrollPane pane=new ScrollPane();
		VBox vBox=new VBox();
		vBox.setPrefWidth(1010);
		
	
		vBox.getChildren().add(barChart);

		pane.setContent(vBox);
	//	System.out.println("do something");
		
		return pane;
		
	}
}