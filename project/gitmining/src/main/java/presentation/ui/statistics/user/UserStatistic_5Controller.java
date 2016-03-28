package presentation.ui.statistics.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import presentation.component.GitLineChart;

public class UserStatistic_5Controller {

	public static VBox getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(UserStatistic_5Controller.class.getResource("userStatistic5.fxml"));
		VBox rootUINode = loader.load();
		UserStatistic_5Controller controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		vMain.getChildren().add(initialChart());
//		this.rightComponentParent = rightComponentParent;

	}

	@FXML
	private VBox vMain;

//	private AnchorPane rightComponentParent;

	public ScrollPane initialChart() {

		List<Number> a = new ArrayList<>();
		a.add(1.0);
		a.add(2.0);
		a.add(3.0);
		a.add(4.0);
		a.add(5.0);
		List<String> headers = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
		GitLineChart lineChart = new GitLineChart(headers, a, "a1", "a2", "a3", "a4");
		ScrollPane pane = new ScrollPane();
		VBox vBox = new VBox();
		vBox.setPrefWidth(1010);
		vBox.getChildren().add(lineChart);
		pane.setContent(vBox);
		return pane;

	}
}
