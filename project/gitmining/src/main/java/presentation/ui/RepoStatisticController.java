package presentation.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
	
	public void initialChart(){
		
	}
}
