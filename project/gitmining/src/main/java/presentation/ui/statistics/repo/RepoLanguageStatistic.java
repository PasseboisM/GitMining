package presentation.ui.statistics.repo;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.RepoDistOverLanguage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
import logic.calc.service.GeneralStatisticsService;
import presentation.component.GitPieChart;
import presentation.image.ImageFactory;
import presentation.ui.statistics.StatisticsPane;

/**
 * 项目语言分类的统计的柱状图
 */

public class RepoLanguageStatistic implements StatisticsPane {
	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(RepoLanguageStatistic.class.getResource("repositoryLanguageStatistic.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RepoLanguageStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
		bgImage=loadImgFile();
		initialImage();
	}

	@FXML
	private AnchorPane anchorPane;
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
		RepoDistOverLanguage languageCounts = statisticsService.getRepoDistOverLanguage();
		GitPieChart pieChart = new GitPieChart(languageCounts);
		anchorPane.getChildren().add(pieChart);
	}
}
