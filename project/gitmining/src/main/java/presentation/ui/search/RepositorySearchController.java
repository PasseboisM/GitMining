package presentation.ui.search;

import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import logic.service.GeneralGetter;
import logic.service.LogicServiceFactory;
import logic.service.SearchService;
import presentation.component.RepositoryMinBlock;
import presentation.image.ImageFactory;

public class RepositorySearchController{
	
	
	public static AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(RepositorySearchController.class.getResource("repositorySearch.fxml"));
		AnchorPane rootUINode = loader.load();
		rootUINode.getStylesheets().add(RepositorySearchController.class.getResource("repoSearch.css").toExternalForm());
		RepositorySearchController controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}
	
	private Image loadImgFile() {
		Image tempImage = null;
		String imageFilename ="searchBackground.jpg";
		try {
			tempImage = ImageFactory.getImageByFileName(imageFilename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	private void initialImage() {
		image = new ImageView();
		image.setImage(bgImage);
		image.setFitWidth(1050);
		image.setFitHeight(675);
//	System.out.println(bgImage.toString()+"1");
//		下两行中请注释掉一行或两行观察区别,建议调整图片大小之后，对于VBox上方控件进行半透明化的css处理
//		repoVBox.getChildren().add(image);
		sonPane.getChildren().add(image);
	}
	

	@FXML	private Button search;
	@FXML   private ToggleButton  noSort,starSort,forkSort;
	@FXML	private FlowPane flowPaneCategory;
	@FXML	private FlowPane flowPaneLanguage;
	@FXML	private VBox repoVBox;
	@FXML 	private Pagination pag;
	@FXML 	private TextField keyword;
	@FXML 	private AnchorPane mainPane;
	@FXML 	private AnchorPane sonPane;
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
	private String[]  keywords = {}; 
	private RepoSortStadard sortStadard;
	private ImageView image;
	
	private static Image bgImage = null;
	
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
		bgImage=loadImgFile();
		initialImage();
		initialCategoryCheckBoxes();
		initialLanguageCheckBoxes();
		initialToggleButtonGroup();
		initialSearchService();
		this.rightComponentParent = rightComponentParent;
		initialPage();
		this.rightComponentParent.getStylesheets().add(RepositorySearchController.class.getResource("repoSearch.css").toExternalForm());
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
		
		languageCheckBoxes.get(0).setOnAction((ActionEvent event)->{
			CheckBox checkBoxAll = (CheckBox) event.getSource();
			if(checkBoxAll.isSelected()){
				//其他设为不选
				for(int i = 1;i<languageCheckBoxes.size();i++){
					languageCheckBoxes.get(i).setSelected(false);
				}
			}
		});
		
		for(int i = 1;i<languageCheckBoxes.size();i++){
			languageCheckBoxes.get(i).setOnAction((ActionEvent event)->{
				CheckBox checkBoxi = (CheckBox) event.getSource();
				if(checkBoxi.isSelected()){
					//all设为不选
					languageCheckBoxes.get(0).setSelected(false);
				}
			});
		}
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
		
		categoryCheckBoxes.get(0).setOnAction((ActionEvent event)->{
			CheckBox checkBoxAll = (CheckBox) event.getSource();
			if(checkBoxAll.isSelected()){
				//其他都设为不选
				for(int i = 1;i<categoryCheckBoxes.size();i++){
					categoryCheckBoxes.get(i).setSelected(false);
				}
				//System.out.println("others set not");
			}
		});
		
		for(int i = 1;i<categoryCheckBoxes.size();i++){
			categoryCheckBoxes.get(i).setOnAction((ActionEvent event)->{
				CheckBox checkBoxi = (CheckBox) event.getSource();
				if(checkBoxi.isSelected()){
					//all设为不选
					categoryCheckBoxes.get(0).setSelected(false);
				}
			});
		}
		
		/*categoryCheckBoxes.get(0).setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});*/
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
		RepositorySearchParam repoSearchParam = new RepositorySearchParam(langs, cates, keywords,sortStadard);
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
	
	private void initialPage(){
		//除10上取整算法 加9之后再除10
		pag.setPageCount((generalGetter.getNumOfRepositories()+9)/10);
		pag.setPageFactory((Integer pageIndex)->initCreatePage(pageIndex));
	}

	private ScrollPane initCreatePage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		int numPerPage = 10;
		List<Repository> listPerPage = null;
		try {
			listPerPage = generalGetter.getRepositories(pageIndex+1, numPerPage, sortStadard);
		} catch (NetworkException e) {
			e.printStackTrace();
		} catch (DataCorruptedException e) {
			e.printStackTrace();
		}
		for (Repository repository : listPerPage) {
			vBox.getChildren().add(new RepositoryMinBlock(rightComponentParent,repository));
		}
		pane.setContent(vBox);
		return pane;
	}

	private ScrollPane createPage(Integer pageIndex) {
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		int numPerPage = 10;
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
