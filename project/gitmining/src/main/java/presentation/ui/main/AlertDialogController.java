package presentation.ui.main;

import java.io.IOException;

import javafx.event.ActionEvent;
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
		if(exitAlert == 1){
			alertStage.close();
		}
		System.out.println(exitAlert);
	}
	
	@FXML
	public void exitAlert(ActionEvent event){
		//TODO 徐江河在更新master分支内容时，此处发生了conflict。
		//现已修改，请做界面的同学看看这是不是正确的版本
		//		05/15
		MainController maincontroller = new MainController();
		maincontroller.exitMain = 1;
		exitAlert = 1;
		showAlert();

	}

}
