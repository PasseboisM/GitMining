package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.message.LoadProgress;
import common.service.Repository;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import logic.service.GeneralGetter;
import logic.service.Loader;
import logic.service.LogicServiceFactory;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance() throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
		controller.initial();
		GeneralGetter generalGetter = LogicServiceFactory.getInstance().getGeneralGetter();
		List<Repository> datas = generalGetter.getRepositories(1, 10, RepoSortStadard.NO_SORT);
		System.out.println(datas.size());
		controller.initialDatas(datas);
		System.out.println("finish");
		return rootUINode;
	}

	@FXML	private Button search;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;

	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
	
	private void initial() {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
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
	
	private void initialDatas(List<Repository> list){
		for (Repository repository : list) {
			RepositoryMinBlock block = new RepositoryMinBlock(repository);
			repoVBox.getChildren().add(block);
		}
	}

	
}
