package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.FakeDataUser;
import presentation.component.UserMinBlock;

public class UserSearchController {
	
	public static AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException{
		FXMLLoader loader = new FXMLLoader(UserSearchController.class.getResource("userSearch.fxml"));
		AnchorPane pane = loader.load();
		UserSearchController controller = loader.getController();
		List<FakeDataUser> datas = controller.getList();
		controller.initial(rightComponentParent, datas);
		controller.initialPage();
		return pane;
	}
	
	
	private void initial(AnchorPane rightComponentParent,List<FakeDataUser> datas) {
		this.rightComponentParent = rightComponentParent;
		this.fakeDatas = datas;
	}
	
	private void initialPage(){
		//除10上取整算法 加9之后再除10
		pag.setPageCount((fakeDatas.size() + 9) / 10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
	}
	
	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		for (int i = 0; i < 10; i++) {
			if (pageIndex*10+i<fakeDatas.size()) {
				vBox.getChildren()
						.add(new UserMinBlock(rightComponentParent, fakeDatas.get(pageIndex * 10 + i)));
			}
		}
		pane.setContent(vBox);
		return pane;
	}
	
	
	@FXML 	private Pagination pag;
	
	private List<FakeDataUser> fakeDatas;
	private AnchorPane rightComponentParent;
	
	private List<FakeDataUser> getList(){
		List<FakeDataUser> list = new ArrayList<FakeDataUser>();
		for (int i = 0; i < 17; i++) {
			list.add(new FakeDataUser("a"+i+"/b"+i,i*10000, "https://github.com/rubinius", "Organization","aaa","http://rubini.us","Everywhere","community@rubini.us","Solve Hard Problems™",60,0,0,0,"2015-9-8", "2010-06-29T18:39:32Z"));
		}
		return list;
	}
}
