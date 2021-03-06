package presentation.ui.statistics.user;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.UserDistOverCreateTime;
import chart_data.service.GeneralStatisticsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import presentation.component.GitParetoChart;
import presentation.component.GitParetoChartController;
import presentation.image.ImageFactory;
import presentation.ui.statistics.StatisticsPane;

public class UserCreateTimeStatisticsPane implements StatisticsPane {

	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(UserCreateTimeStatisticsPane.class.getResource("userCreateTimeStatistics.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserCreateTimeStatisticsPane controller = loader.getController();
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
	
	@FXML
	private AnchorPane anchorPane;
	private ImageView image;
	private static Image bgImage = null;
	
	private AnchorPane rightComponentParent;
	
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
	
	private void initialLayout(AnchorPane rootUINode) {
		AnchorPane.setBottomAnchor(rootUINode, 0.0);
		AnchorPane.setLeftAnchor(rootUINode, 0.0);
		AnchorPane.setRightAnchor(rootUINode, 0.0);
		AnchorPane.setTopAnchor(rootUINode, 0.0);
	}
	
	public void initialChart(){
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		UserDistOverCreateTime userCreateOnTimeCounts = statisticsService.getUserDistOverCreateTime();
//		GitMixChart mixChart = new GitMixChart(userCreateOnTimeCounts);
		GitParetoChartController controller = new GitParetoChartController();
		GitParetoChart paretoChart = controller.createChart(userCreateOnTimeCounts);
		anchorPane.getChildren().add(paretoChart);
//		GitParetoChartController controller = new GitParetoChartController();
//		GitParetoChart paretoChart = controller.createChart();
	}
}
