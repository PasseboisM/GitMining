package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import presentation.component.Radar;

public class RepoDetailsController {
	
	public static BorderPane getInstance(AnchorPane rightComponentParent,Repository repository) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepoDetailsController.class.getResource("repositoryDetails.fxml"));
		BorderPane pane = loader.load();
		RepoDetailsController controller = loader.getController();
		controller.initial(rightComponentParent,repository);
		return pane;
	}
	
	private AnchorPane rightComponentParent;
	private List<Double> marks=new ArrayList<>();
	private List<String> labels=new ArrayList<>();
	@FXML private BorderPane borderPane;
	@FXML private TextField repo_url;
	@FXML private Label labelStar;
	@FXML private Label labelFork;
	@FXML private Label labelWatch;
	@FXML private Label LabelDescription;
	@FXML private Hyperlink labelRepoName;
	@FXML private Hyperlink labelOwnerName;
	@FXML private AnchorPane forRadar;
	
	private void initial(AnchorPane rightComponentParent,Repository repository) {
		initialComponentText(repository);
		fake();
		initialRadar();
		this.rightComponentParent = rightComponentParent;
	}
	
	/*private void initial(AnchorPane rightComponentParent,FakeData repository) {
		initialComponentText(repository);
		this.rightComponentParent = rightComponentParent;
	}*/
	private void fake(){
		marks.add(0, 0.0);
		marks.add(1, 0.5);
		marks.add(2, 0.0);
		marks.add(3, 0.5);
		marks.add(4, 0.0);
		marks.add(5, 0.5);
		labels.add(0, "a");
		labels.add(1, "b");
		labels.add(2, "c");
		labels.add(3, "d");
		labels.add(4, "e");
		labels.add(5, "f");
	}
	
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
	
	private ScrollPane initialRadar(){
		ScrollPane pane=new ScrollPane();
		forRadar.getChildren().add(new Radar(marks,labels));
		pane.setContent(forRadar);
		return pane;
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
