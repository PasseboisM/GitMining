package presentation.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
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
import logic.service.SearchService;
import presentation.component.RepositoryMinBlock;

public class RepositorySearchController{
	
	
	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		VBox rootUINode = loader.load();
		RepositorySearchController controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	@FXML	private Button search;
	@FXML   private ToggleButton  noSort,starSort,forkSort;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;
	@FXML 	private Pagination pag;
	@FXML 	private TextField keyword;
	
	private List<CheckBox> categoryCheckBoxes;
	private List<CheckBox> languageCheckBoxes;
	private AnchorPane rightComponentParent;
	final ToggleGroup Group = new ToggleGroup();
	
	private List<Repository> repositoriesdatas;
	
	private LogicServiceFactory logicServiceFactory;
	private GeneralGetter generalGetter;
	private SearchService searchService;
	
	private Language[] langs;
	private Category[] cates;
	private String[]  keywords = {""}; 
	private RepoSortStadard sortStadard;
	
	@FXML
	private void changeSortStrategy(ActionEvent event) {
		ToggleButton button = (ToggleButton) event.getSource();
		sortStadard=RepoSortStadard.values()[Group.getToggles().indexOf(button)];
		refreshPage();
	}
	
	@FXML
	private void onSearch(ActionEvent event) {
		String key=keyword.getText();
		keywords = key.trim().split(" ");
		refreshLanguages(Language.values());
		refreshCategories(Category.values());
		refreshPage();
	}
	
	
	private void initial(AnchorPane rightComponentParent) {
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		initialToggleButtonGroup();
		initialSearchService();
		this.rightComponentParent = rightComponentParent;
		refreshPage();
	}

	private void initialSearchService() {
		this.logicServiceFactory = LogicServiceFactory.getInstance();
		this.generalGetter = logicServiceFactory.getGeneralGetter();
		this.searchService = logicServiceFactory.getSearchService();
	}

	private void initialToggleButtonGroup() {
		noSort.setToggleGroup(Group);
		noSort.setSelected(true);
		sortStadard = RepoSortStadard.NO_SORT;
		starSort.setToggleGroup(Group);
		forkSort.setToggleGroup(Group);
	}
	
	private void initialLanguageCheckBoxes() {
		//初始化项目语言checkbox
		Language[] languages = Language.values();
		languageCheckBoxes = new ArrayList<CheckBox>(languages.length);
		for (Language language :languages) {
			CheckBox checkBox = new CheckBox(language.getName());
			languageCheckBoxes.add(checkBox);
		}
		languageCheckBoxes.get(0).setSelected(true);
		refreshLanguages(languages);
		flowPaneLanguage.getChildren().addAll(languageCheckBoxes);
	}

	private void refreshLanguages(Language[] languages) {
		List<Language> languagesList = new ArrayList<Language>();
		for (CheckBox checkBox : languageCheckBoxes) {
			if (checkBox.isSelected()) {
				languagesList.add(languages[languageCheckBoxes.indexOf(checkBox)]);
			}
		}
		langs = new Language[languagesList.size()];
		langs = languagesList.toArray(langs);
	}
	
	private void initialCategoryCheckBoxes() {
		//初始化项目类型checkbox
		Category[] categories = Category.values();
		categoryCheckBoxes = new ArrayList<CheckBox>(categories.length);
		for (Category category :categories) {
			CheckBox checkBox = new CheckBox(category.getName());
			categoryCheckBoxes.add(checkBox);
		}
		categoryCheckBoxes.get(0).setSelected(true);
		refreshCategories(categories);
		flowPaneCategory.getChildren().addAll(categoryCheckBoxes);
	}

	private void refreshCategories(Category[] categories) {
		List<Category> categoriesList = new ArrayList<Category>();
		for (CheckBox checkBox : categoryCheckBoxes) {
			if (checkBox.isSelected()) {
				categoriesList.add(categories[categoryCheckBoxes.indexOf(checkBox)]);
			}
		}
		cates = new Category[categoriesList.size()];
		cates = categoriesList.toArray(cates);
	}
	
	private void refreshPage() {
		RepositorySearchParam repoSearchParam = new RepositorySearchParam(langs, cates, keywords);
//		for (Category category : cates) {
//			System.out.println(category.getName());
//		}
//		for (Language language : langs) {
//			System.out.println(language.getName());
//		}
		try {
			this.repositoriesdatas = searchService.searchRepository(repoSearchParam);
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			e.printStackTrace();
		}
		//除10上取整算法 加9之后再除10
		pag.setPageCount((repositoriesdatas.size()+9)/10);
		pag.setPageFactory((Integer pageIndex)->createPage(pageIndex));
	}

	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		int numPerPage = 10;
//		List<Repository> listPerPage = null;
//		try {
//			listPerPage = generalGetter.getRepositories(pageIndex+1, numPerPage, sortStadard);
//		} catch (NetworkException e) {
//			e.printStackTrace();
//		} catch (DataCorruptedException e) {
//			e.printStackTrace();
//		}
		for (int i = 0; i < numPerPage; i++) {
			if (numPerPage * pageIndex + i<repositoriesdatas.size()) {
				vBox.getChildren().add(new RepositoryMinBlock(rightComponentParent,
						repositoriesdatas.get(numPerPage * pageIndex + i)));
			}
		}
		pane.setContent(vBox);
		return pane;
	}
	
}
