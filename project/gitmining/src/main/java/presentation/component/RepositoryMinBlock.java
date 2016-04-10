package presentation.component;

import java.io.IOException;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentation.ui.search.RepoDetailsController;

public class RepositoryMinBlock  extends BorderPane{
	@FXML private Hyperlink repoFullName;
	@FXML private Label description;
	@FXML private Label lastUpdated;
	@FXML private Label stargazersCount;
	@FXML private Label forksCount;
	@FXML private Label subscribersCount;
	private Repository repository;
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
	
	public RepositoryMinBlock(AnchorPane rightComponentParent,Repository repositoryData){
		this();
		this.setComponentText(repositoryData);
		this.repository = repositoryData;
		this.rightComponentParent = rightComponentParent;
	}

	private void setComponentText(Repository repositoryData) {
		repoFullName.setText(repositoryData.getFull_name());
		description.setText(repositoryData.getDescription());
		lastUpdated.setText("Last updated : "+repositoryData.getUpdated_at());
		stargazersCount.setText(repositoryData.getStargazers_count()+"");
		forksCount.setText(repositoryData.getForks_count()+"");
		subscribersCount.setText(repositoryData.getSubscribers_count()+"");
	}
	
	@FXML
	private void jumpToRepositoryDetails() {
		try {
			rightComponentParent.getChildren().add(RepoDetailsController.getInstance(rightComponentParent,repository));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
