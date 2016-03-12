package presentation.component;

import java.io.IOException;

import common.service.GitUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.ui.UserDetailsController;

public class UserMinBlock extends BorderPane{
	
	
	private UserMinBlock(){
		FXMLLoader fxmlLoader = new FXMLLoader(UserMinBlock.class.getResource("userMinBlock.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UserMinBlock(AnchorPane rightComponentParent,GitUser fakeData){
		this();
		this.setComponentText(fakeData);
		this.user = fakeData;
		this.rightComponentParent = rightComponentParent;
	}

	private void setComponentText(GitUser fakeData) {
		userName.setText(fakeData.getLogin());
		userLocation.setText(fakeData.getLocation());
		lastUpdated.setText(fakeData.getUpdated_at());
		followers.setText(fakeData.getFollowers()+"");
		following.setText(fakeData.getFollowing()+"");
		repos.setText(fakeData.getPublic_repos()+"");
	}
	@FXML private Hyperlink userName;
	@FXML private Label userLocation;
	@FXML private Label lastUpdated;
	@FXML private Label followers;
	@FXML private Label following;
	@FXML private Label repos;
	
	private GitUser user;
	
	private AnchorPane rightComponentParent;
	@FXML
	private void jumpToUserDetails() {
		try {
			rightComponentParent.getChildren().add(UserDetailsController.getInstance(rightComponentParent,user));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
