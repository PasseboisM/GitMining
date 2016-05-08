package presentation.ui.main;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class LoginController {
	public static AnchorPane getInstance(AnchorPane rightComponentParent){
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
		//System.out.println("do this");
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
	
	public  String UserName="";
	
	
	
	
	
	
	
	
	private void initial(AnchorPane rightComponentParent) {
		// TODO Auto-generated method stub
		this.rightComponentParent = rightComponentParent;

	}


	@FXML
	private void onLogin() {
		//TODO
		String code;
		code=password.getText();
		if(true){
			UserName=userName.getText();
		}else{
			
		}
		
	}
	
	
	@FXML
	private void onCancel() {
		//TODO
		rightComponentParent.getChildren().remove(loginPane);
	}
	
	


	private AnchorPane rightComponentParent;





	@FXML private AnchorPane loginPane;
	@FXML private TextField userName;
	@FXML private PasswordField password;
	
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	
}
