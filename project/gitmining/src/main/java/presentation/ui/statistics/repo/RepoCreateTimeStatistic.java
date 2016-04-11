package presentation.ui.statistics.repo;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.RepoDistOverCreateTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitBarChart;
import presentation.image.ImageFactory;
import presentation.ui.statistics.StatisticsPane;

public class RepoCreateTimeStatistic implements StatisticsPane {

	/**
	 * 仓库创建时间相关的饼图
	 */

	public AnchorPane getInstance(AnchorPane rightComponentParent) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				RepoCreateTimeStatistic.class.getResource("repositoryCreateTimeStatistic.fxml"));
		AnchorPane rootUINode = loader.load();
		RepoCreateTimeStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
		// this.rightComponentParent=rightComponentParent;
		bgImage=loadImgFile();
		initialImage();
	}

	@FXML
	private AnchorPane anchorPane;
	// private AnchorPane rightComponentParent;
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
		image.setFitWidth(1050);
		image.setFitHeight(675);
		anchorPane.getChildren().add(0,image);
	}

	public void initialChart() {
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		RepoDistOverCreateTime repoCreateOnTimeCounts = statisticsService.getRepoDistOverCreateTime();
		GitBarChart barChart = new GitBarChart(repoCreateOnTimeCounts);
		anchorPane.getChildren().add(barChart);
	}
}
