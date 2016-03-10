package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.Theories;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
		controller.initial(rightComponentParent);
		
		controller.initialDatas(controller.getList());
		
//		GeneralGetter generalGetter = LogicServiceFactory.getInstance().getGeneralGetter();
//		List<Repository> datas = generalGetter.getRepositories(1, 10, RepoSortStadard.NO_SORT);
//		System.out.println(datas.size());
//		controller.initialDatas(datas);
		System.out.println("finish");
		return rootUINode;
	}

	@FXML	private Button search;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;

	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
	
	private AnchorPane rightComponentParent;
	private void initial(AnchorPane rightComponentParent) {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		this.rightComponentParent = rightComponentParent;
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
	
	private void initialDatas(List<FakeData> list){
		for (FakeData repository : list) {
			RepositoryMinBlock block = new RepositoryMinBlock(rightComponentParent,repository);
			repoVBox.getChildren().add(block);
		}
	}
	
	private List<FakeData> getList(){
		List<FakeData> list = new ArrayList<FakeData>();
		for (int i = 0; i < 15; i++) {
			list.add(new FakeData("a"+i+"/b"+i, "description of fake data", "2015-9-8", 58, i+95, 62));
		}
		return list;
	}

	
}
