package presentation.ui.statistics.user;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.UserDistOverType;
import chart_data.service.GeneralStatisticsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import presentation.component.GitPieChart;
import presentation.image.ImageFactory;
import presentation.ui.statistics.StatisticsPane;

public class UserTypeStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(UserTypeStatisticsPane.class.getResource("userTypeStatistics.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserTypeStatisticsPane controller = loader.getController();
		controller.initial(rightComponentParent);
		controller.initialLayout(rootUINode);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
		this.rightComponentParent = rightComponentParent;
		bgImage=loadImgFile();
		initialImage();
	}
	
	private void initialLayout(AnchorPane rootUINode) {
		AnchorPane.setBottomAnchor(rootUINode, 0.0);
		AnchorPane.setLeftAnchor(rootUINode, 0.0);
		AnchorPane.setRightAnchor(rootUINode, 0.0);
		AnchorPane.setTopAnchor(rootUINode, 0.0);
	}

	@FXML
	private AnchorPane anchorPane;
	private AnchorPane rightComponentParent;
	private ImageView image;
	private static Image bgImage = null;
	
	private Image loadImgFile() {
		Image tempImage = null;
		String imageFilename ="Background.jpg";
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
		image.fitWidthProperty().bind(rightComponentParent.widthProperty());
		image.fitHeightProperty().bind(rightComponentParent.heightProperty());
		anchorPane.getChildren().add(0,image);
	}

	public void initialChart() {
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		UserDistOverType userTypeCounts = statisticsService.getUserDistOverType();
		GitPieChart pieChart = new GitPieChart(userTypeCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
