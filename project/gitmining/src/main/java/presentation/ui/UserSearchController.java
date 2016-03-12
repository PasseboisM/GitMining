package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
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
		UserSearchController controller = loader.getController();
//		List<GitUser> datas = controller.getList();
		controller.initial(rightComponentParent);
		controller.initialPage();
		return pane;
	}
	
	
	private void initial(AnchorPane rightComponentParent) {
		this.rightComponentParent = rightComponentParent;
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		
		initialSearchService();
		initialPage();
	}
	
	private void initialPage(){
		//除10上取整算法 加9之后再除10
		UserSearchParam userSearchParam = new UserSearchParam(keyword);
	//	System.out.println(keywords);
		try {
			this.datas = searchService.searchUser(userSearchParam);
		//	System.out.println(keywords.length());
		} catch (NetworkException e) {
			System.out.println("network!");
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			System.out.println("dataCorrupted");
			e.printStackTrace();
		}
	//	System.out.println(datas.size());
		pag.setPageCount((datas.size() + 9) / 10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
		
	}
	
	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		int numPerPage = 10;

		for (int i = 0; i < 10; i++) {
//			if (pageIndex*10+i<datas.size()) {
//				vBox.getChildren()
//						.add(new UserMinBlock(rightComponentParent, datas.get(pageIndex * 10 + i)));
//			}
			vBox.getChildren()
			.add(new UserMinBlock(rightComponentParent,datas.get(numPerPage * pageIndex + i)));
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
	
	@FXML
	private void onSearch(ActionEvent event) {
		String key=vagename.getText();
		keyword = key;
//		System.out.println(keyword);
		initialPage();
	}
	private void initialSearchService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
	
		this.searchService = logicServiceFactory.getSearchService();
		
	}
	
//	private List<GitUser> getList(){
//		List<GitUser> list = new ArrayList<GitUser>();
//		for (int i = 0; i <datas.size(); i++) {
//			list.add(datas.get(i));
//		}
//		return list;
//	}
}
