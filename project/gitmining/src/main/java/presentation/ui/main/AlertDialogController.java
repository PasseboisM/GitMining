package presentation.ui.main;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 用来提示网络未连接
 * @author sj14
 *
 */
public class AlertDialogController {
	@FXML private AnchorPane alertDialog;
	@FXML private Button buttonOut;
	
	public void start(Stage stage){
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("alertDialog.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(pane,300,270);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	private void exitAlert(){
		Platform.exit();
	}

}
