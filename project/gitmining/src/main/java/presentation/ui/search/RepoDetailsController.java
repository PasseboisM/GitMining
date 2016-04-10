package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.Gson;

import common.service.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logic.calc.repo.RepoStatisticsUtil;
import logic.calc.service.RepositoryStatisticsService;
import presentation.component.Radar;
import presentation.image.ImageFactory;

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
	@FXML private AnchorPane forRadar;
	@FXML private Button returnButton;
	private Image btImage=null;
	private ImageView image;
	private void initial(AnchorPane rightComponentParent,Repository repository) {
		btImage=loadImgFile();
		initialButton();
		initialComponentText(repository);
		initialRadar(repository);
		this.rightComponentParent = rightComponentParent;
	}
	
	private Image loadImgFile() {
		Image tempImage = null;
		String imageFilename ="10.png";
		try {
			tempImage = ImageFactory.getImageByFileName(imageFilename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	
	private void initialImage() {
		image = new ImageView();
		image.setImage(btImage);
	

		
	}
	private void initialButton(){
		initialImage();
		returnButton.setGraphic(image);;
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
	
	private void initialRadar(Repository r){
		RepositoryStatisticsService service = new RepoStatisticsUtil();
		Radar radar = null;
		Gson gson = new Gson();
		String json = gson.toJson(r);
		System.out.println(json);
		try {
			radar = new Radar(service.getRanks(r));
		} catch (Exception e) {
			e.printStackTrace();
		}
		forRadar.getChildren().add(radar);
	}
	
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(borderPane);
	}
	
}
