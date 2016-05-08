package presentation.ui.main;

import java.io.IOException;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginController {
	public static AnchorPane getInstance(AnchorPane rightComponentParent){
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginController controller = loader.getController();
		controller.initial(rightComponentParent);
		
		return pane;
	}
	
	
	
	
	
	
	
	
	
	
	private void initial(AnchorPane rightComponentParent) {
		// TODO Auto-generated method stub
		
	}


	@FXML
	private void onLogin() {
		//TODO
	}
	
	
	@FXML
	private void onCancel() {
		//TODO
	}
	
	








	@FXML private AnchorPane loginPane;
	@FXML private TextField userName;
	@FXML private PasswordField password;
	
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	
}
