package presentation.ui.main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlertDialog {
	@FXML private AnchorPane alertDialog;
	@FXML private Button buttonOut;
	void showAlert(){
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("alertDialog.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage alertStage = new Stage();
		Scene scene = new Scene(pane,300,270);
		alertStage.setTitle("提示");
		alertStage.setScene(scene);
		alertStage.show();
	}

}
