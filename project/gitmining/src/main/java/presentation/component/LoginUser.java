package presentation.component;

import java.io.IOException;
import java.net.MalformedURLException;

import common.service.GitUser;
import common.service.GitUserTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import presentation.image.ImageFactory;
import presentation.ui.main.LoginController;

public class LoginUser {
	@FXML
	private ImageView  userV ;
	@FXML
	private  Label userName;
	@FXML
	private Button cancelButton ;
	
	
	private GitUser user;
	private GitUserTest test;
	private AnchorPane rootParent;
	private static Image avatarImage=null;

	
	public static AnchorPane getInstance(AnchorPane userAnchorPane,GitUserTest user){
		FXMLLoader loader = new FXMLLoader(LoginUser.class.getResource("loginUser.fxml"));
		System.out.println("do this");
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginUser controller = loader.getController();
		controller.initial(userAnchorPane,user);
	
		return pane;
	}
	
	
		private void initial(AnchorPane rootParent,GitUserTest gitUser){
		
		loadImgFile();
		this.setComponent(gitUser);
		//this.user = gitUser;
		this.test=gitUser;
		this.rootParent = rootParent;
		initialLayout(rootParent);
		//System.out.println(rootParent);
	}

		private void initialLayout(AnchorPane rootUINode) {
			AnchorPane.setBottomAnchor(rootUINode, 0.0);
			AnchorPane.setLeftAnchor(rootUINode, 0.0);
			AnchorPane.setRightAnchor(rootUINode, 0.0);
			AnchorPane.setTopAnchor(rootUINode, 0.0);
		}
	private void setComponent(GitUserTest gitUser) {
		userName.setText(gitUser.getName());
		Image image = avatarImage;
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
		rootParent.getChildren().remove(this);
	}
	
}
