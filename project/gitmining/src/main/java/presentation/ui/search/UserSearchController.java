package presentation.ui.search;

import java.io.IOException;
import java.util.List;

import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.SearchService;
import presentation.component.UserMinBlock;

public class UserSearchController {
	
	public static AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException{
		FXMLLoader loader = new FXMLLoader(UserSearchController.class.getResource("userSearch.fxml"));
		AnchorPane pane = loader.load();
//		pane.getStylesheets().add(UserSearchController.class.getResource("button.css").toExternalForm());
		UserSearchController controller = loader.getController();
		controller.initial(rightComponentParent);
		return pane;
	}
	
	private void initial(AnchorPane rightComponentParent) {
		this.rightComponentParent = rightComponentParent;
		initialSearchService();
		initialPage();
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
		return pane;
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
		return pane;
	}
	
	
	@FXML 	private Pagination pag;
	@FXML 	private TextField vagename;
	@FXML	private Button search;
	
	
	private List<GitUser> datas;
	private AnchorPane rightComponentParent;
	private String  keyword = ""; 
	private LogicServiceFactory logicServiceFactory;
	private SearchService searchService;
	private GeneralGetter generalGetter;
	
	@FXML
	private void onSearch(ActionEvent event) {
		keyword = vagename.getText();
		refreshPage();
	}
	private void initialSearchService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.searchService = logicServiceFactory.getSearchService();
		this.generalGetter = logicServiceFactory.getGeneralGetter();
	}
	
	
}
