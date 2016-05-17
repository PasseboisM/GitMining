package presentation.ui.statistics.repo;

import java.io.IOException;
import java.net.MalformedURLException;

import chart_data.RepoDistOverCreateTime;
import chart_data.service.GeneralStatisticsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import logic.calc.general.GeneralStatisticsUtil;
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
		initialLayout(rootUINode);
		return rootUINode;
	}

	private void initialLayout(AnchorPane rootUINode) {
		AnchorPane.setBottomAnchor(rootUINode, 0.0);
		AnchorPane.setLeftAnchor(rootUINode, 0.0);
		AnchorPane.setRightAnchor(rootUINode, 0.0);
		AnchorPane.setTopAnchor(rootUINode, 0.0);
	}

	private void initial(AnchorPane rightComponentParent) {
		this.rightComponentParent = rightComponentParent;
		this.initialChart();
		loadImgFile();
		initialImage();
	}
	

	@FXML
	private AnchorPane anchorPane;
	private AnchorPane rightComponentParent;
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
		image.fitWidthProperty().bind(rightComponentParent.widthProperty());
		image.fitHeightProperty().bind(rightComponentParent.heightProperty());
		anchorPane.getChildren().add(0,image);
	}

	public void initialChart() {
		GeneralStatisticsService statisticsService = new GeneralStatisticsUtil();
		RepoDistOverCreateTime repoCreateOnTimeCounts = statisticsService.getRepoDistOverCreateTime();
		GitBarChart barChart = new GitBarChart(repoCreateOnTimeCounts);
		anchorPane.getChildren().add(barChart);
	}
}
