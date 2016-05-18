package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.Recommender;
import logic.service.SearchService;
import presentation.component.TopUserMinBlock;
import presentation.component.UserMinBlock;
import presentation.component.WaitLoader;
import presentation.image.ImageFactory;

public class UserSearchController {
	
	public static AnchorPane getInstance(AnchorPane rightComponentParent){
		FXMLLoader loader = new FXMLLoader(UserSearchController.class.getResource("userSearch.fxml"));
		AnchorPane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserSearchController controller = loader.getController();
		controller.initial(rightComponentParent);
		controller.initialLayout(pane);
		return pane;
	}
	
	private void initial(AnchorPane rightComponentParent) {
		this.rightComponentParent = rightComponentParent;
		initialImage();
		initialSearchService();
		initialPage();
		initialTopRepos();
	}
	
	private void initialTopRepos() {
		WaitLoader waitLoader = new WaitLoader();
		topUsersAnchorPane.getChildren().add(waitLoader);
		
		VBox vBox = new VBox();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					List<GitUser> recommendUsers = recommender.getRecommendUsers();
					System.out.println(recommendUsers.size());
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							List<Node> children = topUsersAnchorPane.getChildren();
							if (children.get(children.size() - 1).equals(waitLoader)) {
								for (GitUser user : recommendUsers) {
									vBox.getChildren().add(new TopUserMinBlock(rightComponentParent, user));
								}
							}
							children.remove(waitLoader);
							topUsersPane.setContent(vBox);
						}
					});
				} catch (NetworkException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(runnable);
		t.start();
		
	}
	
	private void initialImage() {
		try {
			bgImage = ImageFactory.getImageByFileName(ImageFactory.SEARCH_BACKGROUND);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		image = new ImageView();
		image.setImage(bgImage);
		image.fitWidthProperty().bind(rightComponentParent.widthProperty());
		image.fitHeightProperty().bind(rightComponentParent.heightProperty());
		mainPane.getChildren().add(0,image);
	}

	private void initialSearchService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.searchService = logicServiceFactory.getSearchService();
		this.generalGetter = logicServiceFactory.getGeneralGetter();
		this.recommender = logicServiceFactory.getRecommender();
	}

	private void initialPage(){
		pag.setPageCount((generalGetter.getNumOfUsers() + 9) / 10);
		pag.setPageFactory((Integer pageIndex)->initialCreatePage(pageIndex));
	}

	private ScrollPane initialCreatePage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		int numPerPage = 10;
		
		List<GitUser> gitUserPerPage = null;
		try {
			gitUserPerPage = generalGetter.getUsers(pageIndex+1, numPerPage, UserSortSandard.NO_SORT);
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			e.printStackTrace();
		}
		for (GitUser gitUser : gitUserPerPage) {
			vBox.getChildren().add(new UserMinBlock(rightComponentParent, gitUser));
		}
		pane.setContent(vBox);
		vBox.minWidthProperty().bind(pane.widthProperty());
		vBox.maxWidthProperty().bind(pane.widthProperty());
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		return pane;
	}
	
	private void initialLayout(AnchorPane rootUINode) {
		AnchorPane.setBottomAnchor(rootUINode, 0.0);
		AnchorPane.setLeftAnchor(rootUINode, 0.0);
		AnchorPane.setRightAnchor(rootUINode, 0.0);
		AnchorPane.setTopAnchor(rootUINode, 0.0);
	}

	private void refreshPage(){
		//除10上取整算法 加9之后再除10
		UserSearchParam userSearchParam = new UserSearchParam(keyword,UserSortSandard.NO_SORT);
		try {
			this.datas = searchService.searchUser(userSearchParam);
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			e.printStackTrace();
		}
		pag.setPageCount((datas.size() + 9) / 10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
	}
	
	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		int numPerPage = 10;

		for (int i = 0; i < numPerPage; i++) {
			if (numPerPage * pageIndex + i<datas.size()) {
				vBox.getChildren()
						.add(new UserMinBlock(rightComponentParent, datas.get(numPerPage * pageIndex + i)));
			}
		}
		pane.setContent(vBox);
		vBox.minWidthProperty().bind(pane.widthProperty());
		vBox.maxWidthProperty().bind(pane.widthProperty());
		pane.setHbarPolicy(ScrollBarPolicy.NEVER);
		return pane;
	}
	
	
	@FXML 	private Pagination pag;
	@FXML 	private AnchorPane mainPane;
	@FXML	private AnchorPane topUsersAnchorPane;
	@FXML 	private ScrollPane topUsersPane;
	@FXML 	private TextField vagename;
	@FXML	private Button search;
	
	private ImageView image;
	private List<GitUser> datas;
	private AnchorPane rightComponentParent;
	private String  keyword = ""; 
	private LogicServiceFactory logicServiceFactory;
	private SearchService searchService;
	private GeneralGetter generalGetter;
	private Recommender recommender;
	private static Image bgImage = null;
	
	@FXML
	private void onSearch(ActionEvent event) {
		keyword = vagename.getText();
		refreshPage();
	}
	
	
}
