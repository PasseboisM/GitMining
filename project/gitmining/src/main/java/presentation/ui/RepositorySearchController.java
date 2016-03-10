package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import presentation.component.FakeData;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
		List<FakeData> datas = controller.getList();
		controller.initial(rightComponentParent,datas);
		controller.initPage();
		return rootUINode;
	}

	

	@FXML	private Button search,noSort,starSort,forkSort,contributorSort;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;
	@FXML 	private Pagination pag;
	@FXML 	private TextField keyword;
	
	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
	private List<FakeData> fakeDatas;
	private AnchorPane rightComponentParent;
	
	@FXML
	private void onSearch(ActionEvent event) {
		String key=keyword.getText();
		//此处为根据key的搜索算法!
		
		
		System.out.println("The Search For "+key+" in Repository");
	}
	
	@FXML
	private void noSortSearch(ActionEvent event) {

		System.out.println("这个没什么卵用，我觉得可以和楼上的search算法一毛一样");
	}
	
	@FXML
	private void starSortSearch(ActionEvent event) {
	//根据stars排序，需要从数据中得到一个stars的属性对其进行冒泡排序吧
		
		
		System.out.println("这里会有一个冒泡算法吧");
	}
	
	@FXML
	private void forkSortSearch(ActionEvent event) {
		//同上
		
		System.out.println("同上1");
	}
	
	@FXML
	private void contributorSortSearch(ActionEvent event) {
		
		//同上
		
		System.out.println("同上2");
	}
	
	
	
	
	private void initial(AnchorPane rightComponentParent,List<FakeData> datas) {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		this.rightComponentParent = rightComponentParent;
		this.fakeDatas = datas;
	}
	
	private void initialLanguageCheckBoxes() {
		//初始化项目语言checkbox
		Language[] languages = Language.values();
		languageCheckBoxes = new ArrayList<CheckBox>(languages.length);
		for (Language language :languages) {
			CheckBox checkBox = new CheckBox(language.getName());
			languageCheckBoxes.add(checkBox);
		}
		flowPaneLanguage.getChildren().addAll(languageCheckBoxes);
	}
	
	private void initialCategoryCheckBoxes() {
		//初始化项目类型checkbox
		Category[] categories = Category.values();
		categoryCheckBoxes = new ArrayList<CheckBox>(categories.length);
		for (Category category :categories) {
			CheckBox checkBox = new CheckBox(category.getName());
			categoryCheckBoxes.add(checkBox);
		}
		flowPaneCategory.getChildren().addAll(categoryCheckBoxes);
	}
	
	private List<FakeData> getList(){
		List<FakeData> list = new ArrayList<FakeData>();
		for (int i = 0; i < 17; i++) {
			list.add(new FakeData("a"+i+"/b"+i, "description of fake data", "2015-9-8", 58, i+95, 62,"git://github.com/rubinius/rubinius.git"));
		}
		return list;
	}
	
	private void initPage() {
		//除10上取整算法 加9之后再除10
		pag.setPageCount((fakeDatas.size()+9)/10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
	}

	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		for (int i = 0; i < 10; i++) {
			if (pageIndex*10+i<fakeDatas.size()) {
				vBox.getChildren()
						.add(new RepositoryMinBlock(rightComponentParent, fakeDatas.get(pageIndex * 10 + i)));
			}
		}
		pane.setContent(vBox);
		return pane;
	}
	

	
}
