package presentation.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.Bar;
import presentation.component.Line;

public class UserStatisticController {

	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoStatisticController.class.getResource("UserStatistic.fxml"));
//		System.out.println("doing!");
		VBox rootUINode = loader.load();
		UserStatisticController controller = loader.getController();
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
	
	public ScrollPane initialChart(){
		ScrollPane pane=new ScrollPane();
		VBox vBox=new VBox();
		//zhe shui hua de
		//wo shang wo ye xing
//		vBox.getChildren().add(new GitPieChart(null,null));
		vBox.getChildren().add(new Bar(null,null));
		vBox.getChildren().add(new Bar(null,null));
		vBox.getChildren().add(new Bar(null,null));
		vBox.getChildren().add(new Bar(null,null));
		vBox.getChildren().add(new Line(null,null));
		vBox.getChildren().add(new Line(null,null));
		vBox.getChildren().add(new Line(null,null));
		vBox.getChildren().add(new Line(null,null));
		pane.setContent(vBox);
		return pane;
		
	}
}
