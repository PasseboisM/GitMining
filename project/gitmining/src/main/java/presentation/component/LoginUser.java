package presentation.component;

import java.io.IOException;
import java.net.MalformedURLException;

import common.service.GitUser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.service.LogInHelper;
import presentation.image.ImageFactory;


public class LoginUser {

	
	
	public static AnchorPane getInstance(AnchorPane userAnchorPane,GitUser user,LogInHelper logInHelper){
		FXMLLoader loader = new FXMLLoader(LoginUser.class.getResource("loginUser.fxml"));
	//	System.out.println("do this");
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginUser controller = loader.getController();
		controller.initial(userAnchorPane,user,logInHelper);
	
		return pane;
	}
	
	
		private void initial(AnchorPane rootParent,GitUser gitUser, LogInHelper logInHelper){
		
		loadImgFile();
		this.setComponent(gitUser);
		this.user = gitUser;
	//	this.test=gitUser;
		this.rootParent = rootParent;
		this.logInHelper = logInHelper;
		initialLayout(rootParent);
		//System.out.println(rootParent);
	}

		private void initialLayout(AnchorPane rootUINode) {
			AnchorPane.setBottomAnchor(rootUINode, 0.0);
			AnchorPane.setLeftAnchor(rootUINode, 0.0);
			AnchorPane.setRightAnchor(rootUINode, 0.0);
			AnchorPane.setTopAnchor(rootUINode, 0.0);
		}
	private void setComponent(GitUser gitUser) {
		userName.setText(gitUser.getName());
		Image image = avatarImage;
		//Image image = new Image(gitUser.getAvatar_url());
		if(image.isError()){
			image = avatarImage;
		}
		userV.setImage(image);
	}
	
	private void loadImgFile() {
		try {
		
			avatarImage =ImageFactory.getImageByFileName(ImageFactory.AVATAR_DEFAULT);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@FXML
	private void logout(){
		rootParent.getChildren().remove(anchorPane);
		logInHelper.logOut();
	}
	
	@FXML
	private ImageView  userV ;
	@FXML
	private  Label userName;
	@FXML
	private Button cancelButton ;
	@FXML
	private AnchorPane anchorPane ;
	
	@SuppressWarnings("unused")
	private GitUser user;
//	private GitUserTest test;
	private AnchorPane rootParent;
	private static Image avatarImage=null;
	private LogInHelper logInHelper;
	
}
