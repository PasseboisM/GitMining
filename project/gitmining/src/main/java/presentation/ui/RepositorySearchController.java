package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
		
		List<FakeData> datas = controller.getList();
//		controller.initialDatas(datas);
		controller.initial(rightComponentParent,datas);
		controller.initPage();
//		GeneralGetter generalGetter = LogicServiceFactory.getInstance().getGeneralGetter();
//		List<Repository> datas = generalGetter.getRepositories(1, 10, RepoSortStadard.NO_SORT);
//		System.out.println(datas.size());
//		controller.initialDatas(datas);
		System.out.println("finish");
		return rootUINode;
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

	@FXML	private Button search;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;
	@FXML private Pagination pag;


	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
	private List<FakeData> fakeDatas;
	private AnchorPane rightComponentParent;
	private void initial(AnchorPane rightComponentParent,List<FakeData> datas) {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		this.rightComponentParent = rightComponentParent;
		fakeDatas = datas;
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
	

	
}
