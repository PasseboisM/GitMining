package presentation.ui.main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
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
	int exitAlert = 0;
	
	public void showAlert(){
		FXMLLoader loader = new FXMLLoader(MainController.class.getResource("alertDialog.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AlertDialogController controller = loader.getController();
		Stage alertStage = new Stage();
		Scene scene = new Scene(pane,300,270);
		alertStage.setTitle("提示");
		alertStage.setScene(scene);
		alertStage.show();
		exitAlert(null);
		if(exitAlert == 1){
			alertStage.close();
		}
		System.out.println(exitAlert);
	}
	
	@FXML
	public void exitAlert(ActionEvent event){
		MainController maincontroller = new MainController();
		maincontroller.exitMain = 1;
		//exitAlert = 1;
	}

}
