package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import chart_data.radar.UserRanks;
import chart_data.service.UserStatisticsService;
import common.service.GitUser;
import common.service.Repository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
					followers = getter.getFollowerNames(user.getLogin());
					System.out.println("loading related users time used:" + (System.currentTimeMillis() - time1) + "ms");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {fillListUsers(followers, vboxListFollower);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(runnable);
		t.start();
		
		Runnable runnable2 = new Runnable() {
			@Override
			public void run() {
				try {
					long time1 = System.currentTimeMillis();
					following = getter.getFollowingNames(user.getLogin());
					System.out.println("loading related users time used:" + (System.currentTimeMillis() - time1) + "ms");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {fillListUsers(following, vboxListFollowing);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread(runnable2);
		t2.start();
		
		Runnable runnable3 = new Runnable() {
			@Override
			public void run() {
				try {
					long time1 = System.currentTimeMillis();
					ownedRepositories = getter.getOwnedRepositoryNames(user.getLogin());
					System.out.println("loading related repos time used:" + (System.currentTimeMillis() - time1) + "ms");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {fillListRepositories(ownedRepositories,vboxListOwn);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t3 = new Thread(runnable3);
		t3.start();
		
		Runnable runnable4 = new Runnable() {
			@Override
			public void run() {
				try {
					long time1 = System.currentTimeMillis();
					starredRepositories = getter.getStarredRepositoryNames(user.getLogin());
					System.out.println("loading related repos time used:" + (System.currentTimeMillis() - time1) + "ms");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {fillListRepositories(starredRepositories,vboxListStar);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t4 = new Thread(runnable4);
		t4.start();
		
		Runnable runnable5 = new Runnable() {
			@Override
			public void run() {
				try {
					long time1 = System.currentTimeMillis();
					substribedRepositories = getter.getOwnedRepositoryNames(user.getLogin());
					System.out.println("loading related repos time used:" + (System.currentTimeMillis() - time1) + "ms");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {fillListRepositories(substribedRepositories,vboxListSub);}});
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t5 = new Thread(runnable5);
		t5.start();
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
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Image image = new Image(user.getAvatar_url());
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if (image.isError())
							imageView.setImage(avatarImage);
						else
							imageView.setImage(image);
					}
				});
			}
		};
		Thread t = new Thread(runnable);
		t.start();
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
		//TODO
		
		if((""+user.getName()).equals("null")){
			labelBox.getChildren().remove(labelName);
		}else{
			labelName.setText(user.getName());
		}
		if((""+user.getId()).equals("null")){
			labelBox.getChildren().remove(labelId);
		}else{
			labelId.setText("ID : "+user.getId());
		}
		if((""+user.getType()).equals("null")){
			labelBox.getChildren().remove(labelType);
		}else{
			labelType.setText(user.getType());
		}
		if((""+user.getLocation()).equals("null")){
			//System.out.println("do!");
			labelBox.getChildren().remove(labelUserLocation);
		}else{
			
			labelUserLocation.setText(user.getLocation());
		}
		if((user.getEmail()==null)){
			//System.out.println("do!");
			labelBox.getChildren().remove(labelEmail);
		}else{
			//System.out.println(user.getEmail()+"");
			labelEmail.setText(user.getEmail());
		}
		
		if((""+user.getPublic_repos()).equals("null")){
		
			labelBox.getChildren().remove(labelRepos);
		}else{
			
			labelRepos.setText(user.getPublic_repos()+"");
		}
		if(user.getBlog()==null){
			labelBox.getChildren().remove(labelBlog);
		}else{
			labelBlog.setText(""+user.getBlog());
		}
		
		labelFollowers.setText(user.getFollowers()+"");
		labelFollowing.setText(user.getFollowing()+"");
		String createDate=user.getCreated_at().substring(0, 10)+" "+user.getCreated_at().substring(12, user.getCreated_at().length()-1);
		String updateDate=user.getUpdated_at().substring(0, 10)+" "+user.getUpdated_at().substring(12, user.getUpdated_at().length()-1);
		labelCreatedAt.setText(createDate);
		labelUpdatedAt.setText(updateDate);
	}
	@FXML	private Label labelName;
	@FXML	private Label labelId;
	@FXML	private Label labelType;
	@FXML	private Label labelUserLocation;
	@FXML	private Label labelEmail;
	@FXML	private Label labelFollowers;
	@FXML	private Label labelFollowing;
	@FXML	private Label labelRepos;
	@FXML	private Label labelCreatedAt;
	@FXML	private Label labelUpdatedAt;
	@FXML	private Label labelBlog;
	@FXML    private AnchorPane anchorPane;
	@FXML    private ImageView imageView;
	@FXML    private AnchorPane radarAnchorPane;
	@FXML	private Button returnButton;
	@FXML	private VBox vboxListOwn;
	@FXML	private VBox vboxListStar;
	@FXML	private VBox vboxListSub;
	@FXML	private VBox vboxListFollower;
	@FXML	private VBox vboxListFollowing;
	@FXML	private VBox labelBox;
	private static Image btImage=null;
	private static Image avatarImage=null;
	private ImageView imageV;
	private AnchorPane rightComponentParent;
	private List<Repository> ownedRepositories;
	private List<Repository> starredRepositories;
	private List<Repository> substribedRepositories;
	private List<GitUser> followers;
	private List<GitUser> following;
	@FXML
	private void returnToSearchController() {
		rightComponentParent.getChildren().remove(anchorPane);
	}

	private void fillListRepositories(List<Repository> repositories,VBox vBox) {
		for (Repository repository : repositories) {
			Hyperlink hyperlink = new Hyperlink(repository.getFull_name());
			hyperlink.setFont(new Font(18));
			hyperlink.setOnAction(value->{
				rightComponentParent.getChildren().add(RepoDetailsController
						.getInstance(rightComponentParent, repository));
			});
			Separator separator = new Separator();
			vBox.getChildren().addAll(hyperlink,separator);
		}
	}
	
	private void fillListUsers(List<GitUser> users,VBox vBox) {
		for (GitUser user : users) {
			Hyperlink hyperlink = new Hyperlink(user.getLogin());
			hyperlink.setFont(new Font(18));
			hyperlink.setOnAction(value->{
				rightComponentParent.getChildren().add(UserDetailsController
						.getInstance(rightComponentParent, user));
			});
			Separator separator = new Separator();
			vBox.getChildren().addAll(hyperlink,separator);
		}
	}
}
