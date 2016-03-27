package presentation.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.GitBarChart;
import presentation.component.GitPieChart;

public class RepoStatisticController {
	
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoStatisticController.class.getResource("repositoryStatistic.fxml"));
//		System.out.println("doing!");
		VBox rootUINode = loader.load();
		RepoStatisticController controller = loader.getController();
		controller.initial(rightComponentParent);
//		System.out.println("done!");
		return rootUINode;
	}
	private void initial(AnchorPane rightComponentParent) {
		this.rightComponentParent = rightComponentParent;
		initialChart();
	}
	
	
	@FXML	private VBox repoVBox;
	private AnchorPane rightComponentParent;
	//zhe ta ma bu shi zai hua shui?
	//bao cuo de dai ma tong tong shan diao
	public ScrollPane initialChart(){
		ScrollPane pane=new ScrollPane();
		VBox vBox=new VBox();
//		vBox.getChildren().add(new GitPieChart(null));
//		vBox.getChildren().add(new GitBarChart(null,null));
//		vBox.getChildren().add(new GitBarChart(null,null));
//		vBox.getChildren().add(new GitBarChart(null,null));
//		vBox.getChildren().add(new GitBarChart(null,null));
//		vBox.getChildren().add(new GitBarChart(null,null));
//		vBox.getChildren().add(new GitBarChart(null,null));
		pane.setContent(vBox);
		return pane;
		
	}
}
