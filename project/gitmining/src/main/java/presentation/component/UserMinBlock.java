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
	@FXML private Hyperlink userName;
	@FXML private Label location;
	@FXML private Label lastUpdated;
	@FXML private Label followers;
	@FXML private Label following;
	@FXML private Label repos;
	private GitUser user;
	private FakeDataUser fakeData;
	private AnchorPane rightComponentParent;
	
	private UserMinBlock(){
		FXMLLoader fxmlLoader = new FXMLLoader(RepositoryMinBlock.class.getResource("userMinBlock.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UserMinBlock(AnchorPane rightComponentParent,FakeDataUser fakeData){
		this();
		this.setComponentText(fakeData);
		this.fakeData = fakeData;
		this.rightComponentParent = rightComponentParent;
	}

	private void setComponentText(FakeDataUser fakeData) {
		userName.setText(fakeData.getLogin());
		location.setText(fakeData.getLocation());
		lastUpdated.setText(fakeData.getUpdated_at());
		followers.setText(fakeData.getFollowers()+"");
		following.setText(fakeData.getFollowing()+"");
		repos.setText(fakeData.getPublic_repos()+"");
	}
	
	@FXML
	private void jumpToUserDetails() {
		try {
			rightComponentParent.getChildren().add(UserDetailsController.getInstance(rightComponentParent,fakeData));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
