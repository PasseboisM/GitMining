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
import presentation.image.ImageFactory;

public class LoginUser extends AnchorPane {
	@FXML
	private ImageView  userV ;
	@FXML
	private  Label userName;
	@FXML
	private Button cancel ;
	
	
	private GitUser user;
	private AnchorPane rootParent;
	private static Image avatarImage=null;

	
	private LoginUser(){
		FXMLLoader fxmlLoader = new FXMLLoader(UserMinBlock.class.getResource("loginUser.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public LoginUser(AnchorPane rootParent,GitUser gitUser){
		this();
		loadImgFile();
		this.setComponent(gitUser);
		this.user = gitUser;
		
		this.rootParent = rootParent;
	}


	private void setComponent(GitUser gitUser) {
		userName.setText(gitUser.getName());
		Image image = new Image(gitUser.getAvatar_url());
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
