package presentation.component;

import java.io.IOException;

import common.service.Repository;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.ui.FakeData;
import presentation.ui.RepoDetailsController;

public class RepositoryMinBlock  extends BorderPane{
	@FXML private Hyperlink repoFullName;
	@FXML private Label description;
	@FXML private Label lastUpdated;
	@FXML private Label stargazersCount;
	@FXML private Label forksCount;
	@FXML private Label subscribersCount;
	private Repository repository;
	private FakeData fakeData;
	private AnchorPane rightComponentParent;
	
	private RepositoryMinBlock(){
		FXMLLoader fxmlLoader = new FXMLLoader(RepositoryMinBlock.class.getResource("repositoryMinBlock.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public RepositoryMinBlock(AnchorPane rightComponentParent,FakeData fakeData){
		this();
		this.setComponentText(fakeData);
		this.fakeData = fakeData;
		this.rightComponentParent = rightComponentParent;
	}

	private void setComponentText(FakeData fakeData) {
		repoFullName.setText(fakeData.getFull_name());
		description.setText(fakeData.getDescription());
		lastUpdated.setText("Last updated : "+fakeData.getUpdated_at());
		stargazersCount.setText(fakeData.getStargazers_count()+"");
		forksCount.setText(fakeData.getForks_count()+"");
		subscribersCount.setText(fakeData.getSubscribers_count()+"");
	}
	
	@FXML
	private void jumpToRepositoryDetails() {
//		rightComponentParent.getChildren().clear();
		try {
			rightComponentParent.getChildren().add(RepoDetailsController.getInstance(rightComponentParent,fakeData));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
