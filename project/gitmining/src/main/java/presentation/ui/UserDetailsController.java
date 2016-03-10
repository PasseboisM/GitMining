package presentation.ui;

import java.io.IOException;

import common.service.GitUser;
import common.service.Repository;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.component.FakeData;
import presentation.component.FakeDataUser;

public class UserDetailsController {
	public static BorderPane getInstance(AnchorPane rightComponentParent,FakeDataUser user) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoDetailsController.class.getResource("userDetails.fxml"));
		BorderPane pane = loader.load();
		UserDetailsController controller = loader.getController();
		controller.initial(rightComponentParent,user);
		return pane;
		
	}
	private AnchorPane rightComponentParent;
	private void initial(AnchorPane rightComponentParent,GitUser user) {
		this.rightComponentParent = rightComponentParent;
	}
	private void initial(AnchorPane rightComponentParent,FakeDataUser user) {
//		initialComponentText(user);/
		this.rightComponentParent = rightComponentParent;
	}
}
