package presentation.ui.main;

import java.io.IOException;

import common.exception.TargetNotFoundException;
import common.service.GitUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logic.service.LogInHelper;
import logic.service.LogicServiceFactory;


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
		controller.initialLayout(pane);
		return pane;
	}
	

	
	
	
	
	
	
	
	
	private void initial(AnchorPane rightComponentParent) {
		// TODO Auto-generated method stub
		this.rightComponentParent = rightComponentParent;
		initialLoginService();
	}
	
	
	private void initialLoginService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.logInHelper = logicServiceFactory.getLogInHelper();
		
	}

	
	private void initialLayout(AnchorPane rightComponentParent) {
		AnchorPane.setBottomAnchor(rightComponentParent,100.0);
		AnchorPane.setLeftAnchor(rightComponentParent,300.0);
		AnchorPane.setRightAnchor(rightComponentParent,300.0);
		AnchorPane.setTopAnchor(rightComponentParent,100.0);
	}
	
	
	@FXML
	private void onLogin() {
		//TODO
		String code;
		code=password.getText();
		String user;
		user=userName.getText();
		//System.out.println(user);
		try {
			iUser=logInHelper.tryLogIn(user, code);
			
			} catch (TargetNotFoundException e) {
			// TODO Auto-generated catch block
				
			e.printStackTrace();
		
			}
		rightComponentParent.getChildren().remove(loginPane);
		
	}
	
	
	@FXML
	private void onCancel() {
		//TODO
		rightComponentParent.getChildren().remove(loginPane);
	}
	
	


	public GitUser getiUser() {
		return iUser;
	}










	public void setiUser(GitUser iUser) {
		this.iUser = iUser;
	}




	private AnchorPane rightComponentParent;
	private LogicServiceFactory logicServiceFactory;
	private LogInHelper logInHelper;
	private GitUser iUser;


	@FXML private AnchorPane loginPane;
	@FXML private TextField userName;
	@FXML private PasswordField password;
	
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	
}
