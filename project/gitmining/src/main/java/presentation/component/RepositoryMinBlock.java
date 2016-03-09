package presentation.component;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RepositoryMinBlock  extends BorderPane{
	@FXML private Hyperlink repoFullName;
	@FXML private Label description;
	@FXML private Label lastUpdated;
	@FXML private Label stargazersCount;
	@FXML private Label forksCount;
	@FXML private Label subscribersCount;
	
	public RepositoryMinBlock(){
		FXMLLoader fxmlLoader = new FXMLLoader(RepositoryMinBlock.class.getResource("repoPane.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
