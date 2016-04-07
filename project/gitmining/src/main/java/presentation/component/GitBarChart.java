package presentation.component;

import java.io.IOException;
import java.util.Iterator;

import chart_data.RepoDistOverCreateTime;
import chart_data.RepoDistOverCreateTime.RepoCreateOnTimeCount;
import chart_data.RepoDistOverFork;
import chart_data.RepoDistOverFork.ForkNumberRange;
import chart_data.RepoDistOverStar;
import chart_data.RepoDistOverStar.StarCountRange;
import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GitBarChart extends AnchorPane {
	@FXML
	private BarChart<String, Number> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	private static final int DEFAULT_YAXIS_UPPER_BOUND = 100;
	private static final int DEFAULT_FRAME_DURATION = 500;
	
	public GitBarChart(UserDistOverCreateTime userCreateOnTimeCounts) {
		this.initialFXML();
		this.initialText("用户创建时间统计图", "创建时间", "用户个数");
		this.initial(userCreateOnTimeCounts, "用户");
	}
	
	public GitBarChart(RepoDistOverCreateTime repoDistOverCreateTime){
		this.initialFXML();
		this.initialText("项目创建时间统计图", "创建时间", "项目个数");
		this.initial(repoDistOverCreateTime, "项目");
	}
	

	public GitBarChart(RepoDistOverFork repoDistOverFork) {
		this.initialFXML();
		this.initialText("项目复刻数分区间统计图", "复刻数区间", "项目个数");
		this.initial(repoDistOverFork, "项目");
	}
	
	public GitBarChart(RepoDistOverStar repoDistOverStar) {
		this.initialFXML();
		this.initialText("项目关注度分区间统计图", "关注度区间", "项目个数");
		this.initial(repoDistOverStar, "项目");
	}

	private void initialText(String title, String xLabel, String yLabel) {
		barChart.setTitle(title);
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
	}

	private void initialFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(GitBarChart.class.getResource("barChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void initial(RepoDistOverCreateTime repoDistOverCreateTime, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<RepoCreateOnTimeCount> iterator = repoDistOverCreateTime.getCounts();
		int maxCount = DEFAULT_YAXIS_UPPER_BOUND;
		while (iterator.hasNext()) {
			RepoCreateOnTimeCount repoCreateOnTimeCount = iterator.next();
			String lowerBound = repoCreateOnTimeCount.timeLo;
			String higherBound = repoCreateOnTimeCount.timeHi;
			int number = repoCreateOnTimeCount.count;
			maxCount = number>maxCount?number:maxCount;
			XYChart.Data<String,Number> data = new XYChart.Data<>(lowerBound + "~" + higherBound, 0);
			series.getData().add(data);
		}
		yAxis.setAnimated(false);
		yAxis.setUpperBound(maxCount);
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / repoDistOverCreateTime.getNumOfCounts());
		
		 iterator = repoDistOverCreateTime.getCounts();
		for (int i = 0; i < repoDistOverCreateTime.getNumOfCounts(); i++) {
			RepoCreateOnTimeCount repoCreateOnTimeCount = iterator.next();
			String lowerBound = repoCreateOnTimeCount.timeLo;
			String higherBound = repoCreateOnTimeCount.timeHi;
			int number = repoCreateOnTimeCount.count;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("创建时间从"+lowerBound+"至"+higherBound+"的项目个数："+number+"个");
			Tooltip.install(node, tooltip);
		}
		
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(DEFAULT_FRAME_DURATION),
		    (ActionEvent actionEvent) -> {
		    	final Iterator<RepoCreateOnTimeCount> countIterator = repoDistOverCreateTime.getCounts();
				series.getData().stream().forEach((theData) -> {
					RepoCreateOnTimeCount repoCreateOnTimeCount = countIterator.next();
					int number = repoCreateOnTimeCount.count;
	    			theData.setYValue(number);
		            });
		    }
		));
		tl.play();
		
	}
	private void initial(RepoDistOverFork repoDistOverFork, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<ForkNumberRange> iterator = repoDistOverFork.getRepositoryRanges();
		int maxCount = DEFAULT_YAXIS_UPPER_BOUND;
		while (iterator.hasNext()) {
			ForkNumberRange forkNumberRange = iterator.next();
			int lowerBound = forkNumberRange.lowerBound;
			int higherBound = forkNumberRange.higherBound;
			int number = forkNumberRange.numOfRepos;
			maxCount = number>maxCount?number:maxCount;
			XYChart.Data<String,Number> data = new XYChart.Data<>(lowerBound + "-" + higherBound, 0);
			series.getData().add(data);
		}
		yAxis.setAnimated(false);
		yAxis.setUpperBound(maxCount);
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / repoDistOverFork.getNumOfRanges());
		
		 iterator = repoDistOverFork.getRepositoryRanges();
		for (int i = 0; i < repoDistOverFork.getNumOfRanges(); i++) {
			ForkNumberRange forkNumberRange = iterator.next();
			int lowerBound = forkNumberRange.lowerBound;
			int higherBound = forkNumberRange.higherBound;
			int number = forkNumberRange.numOfRepos;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("复刻数从"+lowerBound+"至"+higherBound+"的项目个数："+number+"个");
			Tooltip.install(node, tooltip);
		}
		
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(DEFAULT_FRAME_DURATION),
		    (ActionEvent actionEvent) -> {
		    	final Iterator<ForkNumberRange> countIterator = repoDistOverFork.getRepositoryRanges();
				series.getData().stream().forEach((theData) -> {
					ForkNumberRange forkNumberRange = countIterator.next();
					int number = forkNumberRange.numOfRepos;
	    			theData.setYValue(number);
		            });
		    }
		));
		tl.play();
	}
	
	private void initial(RepoDistOverStar repoDistOverStar, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<StarCountRange> iterator = repoDistOverStar.getRanges();
		int maxCount = DEFAULT_YAXIS_UPPER_BOUND;
		while (iterator.hasNext()) {
			StarCountRange starCountRange = iterator.next();
			int lowerBound = starCountRange.lowerStar;
			int higherBound = starCountRange.higherStar;
			int number = starCountRange.numOfRepos;
			maxCount = number>maxCount?number:maxCount;
			XYChart.Data<String,Number> data = new XYChart.Data<>(lowerBound + "-" + higherBound, 0);
			series.getData().add(data);
		}
		yAxis.setAnimated(false);
		yAxis.setUpperBound(maxCount);
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / repoDistOverStar.getNumOfRanges());
		
		 iterator = repoDistOverStar.getRanges();
		for (int i = 0; i < repoDistOverStar.getNumOfRanges(); i++) {
			StarCountRange starCountRange = iterator.next();
			int lowerBound = starCountRange.lowerStar;
			int higherBound = starCountRange.higherStar;
			int number = starCountRange.numOfRepos;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("复刻数从"+lowerBound+"至"+higherBound+"的项目个数："+number+"个");
			Tooltip.install(node, tooltip);
		}
		
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(DEFAULT_FRAME_DURATION),
		    (ActionEvent actionEvent) -> {
		    	final Iterator<StarCountRange> countIterator = repoDistOverStar.getRanges();
				series.getData().stream().forEach((theData) -> {
					StarCountRange starCountRange = countIterator.next();
					int number = starCountRange.numOfRepos;
	    			theData.setYValue(number);
		            });
		    }
		));
		tl.play();
	}
	

	private void initial(UserDistOverCreateTime userCreateOnTimeCounts, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<UserCreateOnTimeCount> countIterator = userCreateOnTimeCounts.getCounts();
		int maxCount = DEFAULT_YAXIS_UPPER_BOUND;
		while (countIterator.hasNext()) {
			UserCreateOnTimeCount languageCount = countIterator.next();
			String createTime = languageCount.timeLo;
			int count = languageCount.count;
			maxCount = count>maxCount?count:maxCount;
			XYChart.Data<String,Number> data = new XYChart.Data<>(createTime, 0);
			series.getData().add(data);
		}
		yAxis.setAnimated(false);
		yAxis.setUpperBound(maxCount);
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / userCreateOnTimeCounts.getNumOfCount());

		countIterator = userCreateOnTimeCounts.getCounts();
		for (int i = 0; i < userCreateOnTimeCounts.getNumOfCount(); i++) {
			UserCreateOnTimeCount languageCount = countIterator.next();
			int count = languageCount.count;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("用户个数："+count+"个");
			Tooltip.install(node, tooltip);
		}
		
		Timeline tl = new Timeline();
		tl.getKeyFrames().add(new KeyFrame(Duration.millis(DEFAULT_FRAME_DURATION),
		    (ActionEvent actionEvent) -> {
		    	final Iterator<UserCreateOnTimeCount> iterator = userCreateOnTimeCounts.getCounts();
				series.getData().stream().forEach((theData) -> {
					UserCreateOnTimeCount languageCount = iterator.next();
					int count = languageCount.count;
	    			theData.setYValue(count);
		            });
		    }
		));
		tl.play();
	}
}
