package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import chart_data.radar.UserRanks;
import chart_data.service.UserStatisticsService;
import common.service.GitUser;
import common.service.Repository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.service.LogicServiceFactory;
import logic.service.UserRelatedListGetter;
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
		controller.initialLayout(pane);
		return pane;
	}
	private AnchorPane rightComponentParent;
	private List<Repository> repositories;
	private void initial(AnchorPane rightComponentParent,GitUser user) {
		this.rightComponentParent = rightComponentParent;
		loadImgFile();
		initialButton();
		initialComponentText(user);
		initialAvatarImage(user);
		initialRadar(user);
		initialComboBox(user);
	}

	private void initialComboBox(GitUser user) {
		
		UserRelatedListGetter getter = LogicServiceFactory.getInstance().getUserRelatedListGetter();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					long time1 = System.currentTimeMillis();
					repositories = getter.getOwnedRepositoryNames(user.getLogin());
					System.out.println("loading related repos time used:" + (System.currentTimeMillis() - time1) + "ms");
					ObservableList<String> options = FXCollections.observableArrayList();
					repositories.forEach(repository->{
						options.add(repository.getFull_name());
					});
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							repoComboBox.setItems(options);
							repoComboBox.setOnAction(value->{
								int index = repoComboBox.getSelectionModel().getSelectedIndex();
								try {
									rightComponentParent.getChildren().add(RepoDetailsController
											.getInstance(rightComponentParent, repositories.get(index)));
								} catch (Exception e) {
									System.out.println("excuse me?");
								}
								Platform.runLater(new Runnable() {
									@Override
									public void run() {repoComboBox.getSelectionModel().clearSelection();}});
							});
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(runnable);
		t.start();
		
	}

	private void initialRadar(GitUser user) {
		UserStatisticsService service = LogicServiceFactory.getInstance().getStatisticsMaker().getUserStatistics();
		UserRanks ranks = service.getRanks(user);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Radar radar = new Radar(ranks);
				radarAnchorPane.getChildren().add(radar);
			}
		});
	}

	private void initialAvatarImage(GitUser user) {
		Image image = new Image(user.getAvatar_url());
		if(image.isError()){
			image = avatarImage;
		}
		imageView.setImage(image);
	}
	
	private void initialLayout(AnchorPane rootUINode) {
		AnchorPane.setBottomAnchor(rootUINode, 0.0);
		AnchorPane.setLeftAnchor(rootUINode, 0.0);
		AnchorPane.setRightAnchor(rootUINode, 0.0);
		AnchorPane.setTopAnchor(rootUINode, 0.0);
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
	@FXML	private Button returnButton;
	@FXML	private ComboBox<String> repoComboBox;
	private static Image btImage=null;
	private static Image avatarImage=null;
	private ImageView imageV;
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(anchorPane);
	}
}
