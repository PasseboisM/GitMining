package presentation.component;

import java.io.IOException;
import java.util.List;

import common.service.GitUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.ui.search.UserDetailsController;

public class TopUserMinBlock extends BorderPane{
	
	
	private TopUserMinBlock(){
		FXMLLoader fxmlLoader = new FXMLLoader(TopUserMinBlock.class.getResource("topUserBlock.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TopUserMinBlock(AnchorPane rightComponentParent,GitUser gitUser){
		this();
		this.setComponentText(gitUser);
		this.user = gitUser;
		this.rightComponentParent = rightComponentParent;
	}

	private void setComponentText(GitUser userData) {
		userLogin.setText(userData.getLogin());
		followers.setText(userData.getFollowers()+"");
		following.setText(userData.getFollowing()+"");
	}
	@FXML private Hyperlink userLogin;
	@FXML private Label followers;
	@FXML private Label following;
	
	private GitUser user;
	
	private AnchorPane rightComponentParent;
	@FXML
	private void jumpToUserDetails() {
		WaitLoader waitLoader = new WaitLoader();
		rightComponentParent.getChildren().add(waitLoader);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				AnchorPane anchorPane = UserDetailsController.getInstance(rightComponentParent,user);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						List<Node> childred = rightComponentParent.getChildren();
						if (childred.get(childred.size()-1).equals(waitLoader)) {
							childred.add(anchorPane);
						}
						childred.remove(waitLoader);
					}
				});
			}
		};
		Thread t = new Thread(runnable);
		t.start();
	}
}
