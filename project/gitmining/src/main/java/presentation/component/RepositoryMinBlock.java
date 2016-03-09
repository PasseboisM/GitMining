package presentation.component;

import java.io.IOException;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RepositoryMinBlock  extends BorderPane{
	@FXML private Hyperlink repoFullName;
	@FXML private Label description;
	@FXML private Label lastUpdated;
	@FXML private Label stargazersCount;
	@FXML private Label forksCount;
	@FXML private Label subscribersCount;
	private Repository repository;
	
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
	
	public RepositoryMinBlock(Repository repository){
		this();
		this.setComponentText(repository);
		this.repository = repository;
	}

	private void setComponentText(Repository repository) {
		repoFullName.setText(repository.getFull_name());
		description.setText(repository.getDescription());
		lastUpdated.setText("Last updated : "+repository.getUpdated_at());
		stargazersCount.setText(repository.getStargazers_count()+"");
		forksCount.setText(repository.getForks_count()+"");
		subscribersCount.setText(repository.getSubscribers_count()+"");
	}
	
	@FXML
	private void jumpToRepositoryDetails() {
		//点击超链接，进入项目细节
	}
}
