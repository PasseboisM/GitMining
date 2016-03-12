package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.service.Repository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
//		List<Repository> datas = controller.getList();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	@FXML	private Button search;
	@FXML   private ToggleButton  noSort,starSort,forkSort,contributorSort;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;
	@FXML 	private Pagination pag;
	@FXML 	private TextField keyword;
	
	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
//	private List<Repository> repoDatas;
	private AnchorPane rightComponentParent;
	final ToggleGroup Group = new ToggleGroup();
	
	private LogicServiceFactory logicServiceFactory;
	private GeneralGetter generalGetter;
	

	
	@FXML
	private void onSearch(ActionEvent event) {
		String key=keyword.getText();
		System.out.println("The Search For "+key+" in Repository");
	}
	
	@FXML
	private void noSortSearch(ActionEvent event) {
		System.out.println("这个没什么卵用，我觉得可以和楼上的search算法一毛一样");
	}
	
	@FXML
	private void starSortSearch(ActionEvent event) {
		System.out.println("这里会有一个冒泡算法吧");
	}
	
	@FXML
	private void forkSortSearch(ActionEvent event) {
		System.out.println("同上1");
	}
	
	@FXML
	private void contributorSortSearch(ActionEvent event) {
		System.out.println("同上2");
	}
	
	private void initial(AnchorPane rightComponentParent) {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		initialToggleButtonGroup();
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.generalGetter = logicServiceFactory.getGeneralGetter();
		this.rightComponentParent = rightComponentParent;
//		this.repoDatas = datas;
		initPage();
	}

	private void initialToggleButtonGroup() {
		noSort.setToggleGroup(Group);
		noSort.setSelected(true);
		starSort.setToggleGroup(Group);
		forkSort.setToggleGroup(Group);
		contributorSort.setToggleGroup(Group);
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
	/*
	private List<Repository> getList(){
		List<Repository> list = new ArrayList<Repository>();
//		for (int i = 0; i < 5000; i++) {
//			list.add(new Repository("a"+i+"/b"+i, "description of fake data", "2015-9-8", 58, i+95, 62,"git://github.com/rubinius/rubinius.git"));
//		}
//		return list;
		return list;
	}*/
	
	private void initPage() {
		//除10上取整算法 加9之后再除10
		pag.setPageCount((generalGetter.getNumOfRepositories()+9)/10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
	}

	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		int numPerPage = 10;
		
		
		List<Repository> listPerPage = null;
		try {
			listPerPage = generalGetter.getRepositories(pageIndex+1, numPerPage, RepoSortStadard.NO_SORT);
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			e.printStackTrace();
		}
		
		
		
		for (int i = 0; i < numPerPage; i++) {
			vBox.getChildren().add(new RepositoryMinBlock(rightComponentParent, listPerPage.get(i)));
		}
		pane.setContent(vBox);
		return pane;
	}
	

	
}
