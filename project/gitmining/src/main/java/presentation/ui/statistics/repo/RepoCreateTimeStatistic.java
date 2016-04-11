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

	public AnchorPane getInstance(AnchorPane rightComponentParent) {
		FXMLLoader loader = new FXMLLoader(
				RepoCreateTimeStatistic.class.getResource("repositoryCreateTimeStatistic.fxml"));
		AnchorPane rootUINode = null;
		try {
			rootUINode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		RepoCreateTimeStatistic controller = loader.getController();
		controller.initial(rightComponentParent);
		return rootUINode;
	}

	private void initial(AnchorPane rightComponentParent) {
		this.initialChart();
		loadImgFile();
		initialImage();
	}

	@FXML
	private AnchorPane anchorPane;
    private ImageView image;
	private Image bgImage = null;
	
	private void loadImgFile() {
		try {
			bgImage = ImageFactory.getImageByFileName(ImageFactory.STATISTICS_REPO_CREATE_TIME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
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
