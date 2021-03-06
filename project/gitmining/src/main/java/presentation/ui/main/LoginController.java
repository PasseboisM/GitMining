package presentation.ui.main;

import java.io.IOException;

import common.exception.TargetNotFoundException;
import common.model.beans.GitUserBeans;
import common.service.GitUser;
import common.service.GitUserTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import logic.service.LogInHelper;
import logic.service.LogicServiceFactory;
import presentation.component.LoginUser;


public class LoginController {
	public static AnchorPane getInstance(AnchorPane userAnchorPane,AnchorPane rightComponentParent){
		FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
		//System.out.println("do this");
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginController controller = loader.getController();
		controller.initial(userAnchorPane,rightComponentParent);
		controller.initialLayout(pane);
		return pane;
	}
	

	
	
	
	
	
	
	private void initial(AnchorPane userAnchorPane,AnchorPane rightComponentParent) {
		// TODO Auto-generated method stub
		this.rightComponentParent = rightComponentParent;
		this.userAnchorPane = userAnchorPane;
		initialLoginService();
		//codeLabel.setText("密码：");
	}
	
	
	private void initialLoginService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.logInHelper = logicServiceFactory.getLogInHelper();
		
	}

	
	private void initialLayout(AnchorPane rightComponentParent) {
	//	AnchorPane.setBottomAnchor(rightComponentParent,150.0);
		AnchorPane.setLeftAnchor(rightComponentParent,340.0);
		AnchorPane.setRightAnchor(rightComponentParent,340.0);
		AnchorPane.setTopAnchor(rightComponentParent,80.0);
	}
	
	
	@FXML
	private void onLogin() {
	//	System.out.println(userAnchorPane);
		//TODO
		String code;
		code=password.getText();
		String user;
		user=userName.getText();
		boolean isLogLegal=false ;
	//未实现部分先等待，采用假数据尝试	
		try {
		testUser=	logInHelperT.tryLogIn(user, code);
			//iUser=logInHelper.tryLogIn(user, code);
			isLogLegal=true;
			} catch (TargetNotFoundException e) {
				 isLogLegal=false ;
		
			}
		if(isLogLegal){
			rightComponentParent.getChildren().remove(loginPane);
			AnchorPane anchorPane = LoginUser.getInstance(userAnchorPane,testUser,logInHelperT);
			userAnchorPane.getChildren().add(anchorPane);
		}else{
			warnLabel.setText("用户名或密码错误请重新输入！");
		}
		
		
		
	//	uuuser.setImage_url("abcdefg");
	//	uuuser.setName("LiuQing");
		
	
		
	}
	
	
	@FXML
	private void onCancel() {
		//TODO
		rightComponentParent.getChildren().remove(loginPane);
	}
	
	


	




	private AnchorPane rightComponentParent;
	private LogicServiceFactory logicServiceFactory;
	private LogInHelper logInHelper;
	private LogInHelperTest logInHelperT=new LogInHelperTest();
	private GitUserBeans iUser;
	private GitUser testUser; 
	private GitUserTest mockUser=new GitUserTest();
	private AnchorPane userAnchorPane;

	@FXML private AnchorPane loginPane;
	@FXML private TextField userName;
	@FXML private PasswordField password;
	@FXML private Label codeLabel,warnLabel;
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	
}
