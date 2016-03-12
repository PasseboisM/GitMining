package presentation.ui;

import java.io.IOException;

import common.service.GitUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class UserDetailsController {
	public static BorderPane getInstance(AnchorPane rightComponentParent,GitUser user) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserDetailsController.class.getResource("userDetails.fxml"));
		BorderPane pane = loader.load();
		UserDetailsController controller = loader.getController();
		controller.initial(rightComponentParent,user);
		return pane;
		
	}
	private AnchorPane rightComponentParent;
	private void initial(AnchorPane rightComponentParent,GitUser user) {
		initialComponentText(user);
		this.rightComponentParent = rightComponentParent;
		
	}

	private void initialComponentText(GitUser user) {
		labelName.setText(user.getName());
		labelId.setText("ID : "+user.getId());
		labelType.setText(user.getType());
		labelBlog.setText(user.getBlog());
		labelUserLocation.setText(user.getLocation());
		labelEmail.setText(user.getEmail());
		labelBio.setText(user.getBio());
		labelFollowers.setText(user.getFollowers()+"");
		labelFollowing.setText(user.getFollowing()+"");
		labelRepos.setText(user.getPublic_repos()+"");
		labelCreatedAt.setText(user.getCreated_at());
		labelUpdatedAt.setText(user.getUpdated_at());
	}
	@FXML	private Label labelName;
	@FXML	private Label labelId;
	@FXML	private Label labelType;
	@FXML	private Label labelBlog;
	@FXML	private Label labelUserLocation;
	@FXML	private Label labelEmail;
	@FXML	private Label labelBio;
	@FXML	private Label labelFollowers;
	@FXML	private Label labelFollowing;
	@FXML	private Label labelRepos;
	@FXML	private Label labelCreatedAt;
	@FXML	private Label labelUpdatedAt;
	@FXML    private BorderPane borderPane;
	
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(borderPane);
	}
}
