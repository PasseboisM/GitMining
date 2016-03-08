package presentation.ui;
import java.io.IOException;
import java.util.List;

import common.service.RepositoryMin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Box {
	@FXML	private TableView<RepositoryMin> tableView;
	@FXML	private TableColumn<RepositoryMin, Integer> id;
	@FXML	private TableColumn<RepositoryMin, String> full_name;
	@FXML	private TableColumn<RepositoryMin, String> name;
	@FXML	private TableColumn<RepositoryMin, Boolean> isPrivate;
	@FXML	private TableColumn<RepositoryMin, Integer> star;
	@FXML	private TableColumn<RepositoryMin, Integer> fork;
	@FXML   public Button button;
	private ObservableList<RepositoryMin> repoListData;
	
	public static VBox getInstance() throws IOException{
		FXMLLoader loader = new FXMLLoader(Box.class.getResource("box.fxml"));
		VBox vbox = loader.load();
//		Box controller = loader.getController();
//		List<RepositoryMin> repositoryMins = new ArrayList<RepositoryMin>();
//		for (int i = 0; i < 10; i++) {
//			repositoryMins.add(new RepositoryMinBeans(i, i+"/test"+i, "test"+i, true, i*10, i));
//		}
//		controller.initial();
//		controller.initDatas(repositoryMins);
		return vbox;
	}
	
	private void initial() {
		id.setCellValueFactory(new PropertyValueFactory<RepositoryMin, Integer>("id"));
		full_name.setCellValueFactory(new PropertyValueFactory<RepositoryMin, String>("full_name"));
		name.setCellValueFactory(new PropertyValueFactory<RepositoryMin, String>("name"));
		isPrivate.setCellValueFactory(new PropertyValueFactory<RepositoryMin, Boolean>("isPrivate"));
		star.setCellValueFactory(new PropertyValueFactory<RepositoryMin, Integer>("stargazers_count"));
		fork.setCellValueFactory(new PropertyValueFactory<RepositoryMin, Integer>("forks_count"));
	}

	private void initDatas(List<RepositoryMin>	repositoryMins) {
		repoListData = FXCollections.observableArrayList(repositoryMins);
		tableView.setItems(repoListData);
	}
}