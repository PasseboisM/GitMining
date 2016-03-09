package presentation.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class UserSearchController {
	
	public static AnchorPane getInstance() throws IOException{
		FXMLLoader loader = new FXMLLoader(UserSearchController.class.getResource("userSearch.fxml"));
		AnchorPane pane = loader.load();
		return pane;
	}
}
