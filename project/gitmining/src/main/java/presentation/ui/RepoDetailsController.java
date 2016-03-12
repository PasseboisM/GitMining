package presentation.ui;

import java.io.IOException;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class RepoDetailsController {
	
	public static BorderPane getInstance(AnchorPane rightComponentParent,Repository repository) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoDetailsController.class.getResource("repositoryDetails.fxml"));
		BorderPane pane = loader.load();
		RepoDetailsController controller = loader.getController();
		controller.initial(rightComponentParent,repository);
		return pane;
	}
	
	private AnchorPane rightComponentParent;
	@FXML private BorderPane borderPane;
	@FXML private TextField repo_url;
	@FXML private Label labelStar;
	@FXML private Label labelFork;
	@FXML private Label labelWatch;
	@FXML private Label LabelDescription;
	@FXML private Hyperlink labelRepoName;
	@FXML private Hyperlink labelOwnerName;
	
	private void initial(AnchorPane rightComponentParent,Repository repository) {
		initialComponentText(repository);
		this.rightComponentParent = rightComponentParent;
	}
	
	/*private void initial(AnchorPane rightComponentParent,FakeData repository) {
		initialComponentText(repository);
		this.rightComponentParent = rightComponentParent;
	}*/
	
	private void initialComponentText(Repository repository) {
		repo_url.setText(repository.getGit_url());
		labelStar.setText(repository.getStargazers_count()+"");
		labelFork.setText(repository.getForks_count()+"");
		labelWatch.setText(repository.getSubscribers_count()+"");
		LabelDescription.setText(repository.getDescription());
		String full_name = repository.getFull_name();
		assert full_name.split("/").length==2;
		labelOwnerName.setText(full_name.split("/")[0]);
		labelRepoName.setText(full_name.split("/")[1]);
	}
	
	/*private void initialComponentText(FakeData repository) {
		repo_url.setText(repository.getGit_url());
		labelStar.setText(repository.getStargazers_count()+"");
		labelFork.setText(repository.getForks_count()+"");
		labelWatch.setText(repository.getSubscribers_count()+"");
		LabelDescription.setText(repository.getDescription());
		String full_name = repository.getFull_name();
		assert full_name.split("/").length==2;
		labelOwnerName.setText(full_name.split("/")[0]);
		labelRepoName.setText(full_name.split("/")[1]);
	}*/
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(borderPane);
	}
	
}
