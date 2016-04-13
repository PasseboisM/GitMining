package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.radar.UserRanks;
import common.service.GitUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.service.UserStatisticsService;
import logic.calc.user.UserStatisticsUtil;
import presentation.component.Radar;
import presentation.image.ImageFactory;

public class UserDetailsController {
	public static AnchorPane getInstance(AnchorPane rightComponentParent,GitUser user)  {
		FXMLLoader loader = new FXMLLoader(UserDetailsController.class.getResource("userDetails.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserDetailsController controller = loader.getController();
		controller.initial(rightComponentParent,user);
		return pane;
	}
	private AnchorPane rightComponentParent;
	private void initial(AnchorPane rightComponentParent,GitUser user) {
		loadImgFile();
		initialButton();
		initialComponentText(user);
		this.rightComponentParent = rightComponentParent;
		
		Image image = new Image(user.getAvatar_url());
		if(image.isError()){
			image = avatarImage;
		}
		//System.out.println(""+user.getAvatar_url().toString());
	//	System.out.println("Image error?->"+image.isError());
		imageView.setImage(image);
		
		UserStatisticsService service = new UserStatisticsUtil();
		UserRanks ranks = service.getRanks(user);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Radar radar = new Radar(ranks);
				radarAnchorPane.getChildren().add(radar);
			}
		});
	}
	private void loadImgFile() {
		try {
			btImage = ImageFactory.getImageByFileName(ImageFactory.BACK);
			avatarImage =ImageFactory.getImageByFileName(ImageFactory.AVATAR_DEFAULT);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void initialImage() {
		imageV = new ImageView();
		imageV.setImage(btImage);
		imageV.setFitWidth(60);
		imageV.setFitHeight(60);
	}
	private void initialButton(){
		initialImage();
		returnButton.setGraphic(imageV);
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
	@FXML    private AnchorPane anchorPane;
	@FXML    private ImageView imageView;
	@FXML    private AnchorPane radarAnchorPane;
	@FXML private Button returnButton;
	private static Image btImage=null;
	private static Image avatarImage=null;
	private ImageView imageV;
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(anchorPane);
	}
}
