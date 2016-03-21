package presentation.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class RepoStatisticController {
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoStatisticController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
//		RepositorySearchController controller = loader.getController();
//		controller.initial(rightComponentParent);
		return rootUINode;
	}
	private void initial(AnchorPane rightComponentParent) {
		
	}
	
}
