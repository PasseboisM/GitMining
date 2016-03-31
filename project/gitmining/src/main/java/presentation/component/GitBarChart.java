package presentation.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import chart_data.RepoDistOverFork;
import chart_data.RepoDistOverFork.ForkNumberRange;
import chart_data.RepoDistOverLanguage;
import chart_data.RepoDistOverStar;
import chart_data.RepoDistOverStar.StarCountRange;
import chart_data.RepoDistOverLanguage.LanguageCount;
import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import common.enumeration.attribute.Language;
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

public class GitBarChart extends AnchorPane {
	@FXML
	private BarChart<String, Number> barChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	/**
	 * 柱状图构造函数
	 */
	public GitBarChart(List<String> columns, List<Number> values, String seriesName, String title, String xLabel,
			String yLabel) {
		this.initialFXML();
		this.initialText(title, xLabel, yLabel);
		this.initial(columns, values, seriesName);
	}
	
	public GitBarChart(RepoDistOverLanguage languageCounts) {
		this.initialFXML();
		this.initialText("项目使用语言统计图", "语言", "项目个数");
		this.initial(languageCounts, "项目");
	}
	
	public GitBarChart(UserDistOverCreateTime userCreateOnTimeCounts) {
		this.initialFXML();
		this.initialText("用户创建时间统计图", "创建时间", "用户个数");
		this.initial(userCreateOnTimeCounts, "用户");
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

	/**
	 * 初始化柱状图
	 */
	private void initial(List<String> columns, List<Number> values, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		for (int i = 0; i < columns.size(); i++) {
			XYChart.Data<String,Number> data = new XYChart.Data<>(columns.get(i), values.get(i));
			series.getData().add(data);
		}
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / columns.size());

		for (int i = 0; i < columns.size(); i++) {
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip(String.valueOf(values.get(i)));
			Tooltip.install(node, tooltip);
		}

	}
	
	private void initial(RepoDistOverFork repoDistOverFork, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<ForkNumberRange> iterator = repoDistOverFork.getRepositoryRanges();
		while (iterator.hasNext()) {
			ForkNumberRange forkNumberRange = iterator.next();
			int lowerBound = forkNumberRange.lowerBound;
			int higherBound = forkNumberRange.higherBound;
			int number = forkNumberRange.numOfRepos;
			XYChart.Data<String,Number> data = new XYChart.Data<>(lowerBound + "-" + higherBound, number);
			series.getData().add(data);
		}
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
			Tooltip tooltip = new Tooltip("复刻数了"+lowerBound+"至"+higherBound+"的项目个数："+number+"个");
			Tooltip.install(node, tooltip);
		}
	}
	
	private void initial(RepoDistOverStar repoDistOverStar, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<StarCountRange> iterator = repoDistOverStar.getRanges();
		while (iterator.hasNext()) {
			StarCountRange starCountRange = iterator.next();
			int lowerBound = starCountRange.lowerStar;
			int higherBound = starCountRange.higherStar;
			int number = starCountRange.numOfRepos;
			XYChart.Data<String,Number> data = new XYChart.Data<>(lowerBound + "-" + higherBound, number);
			series.getData().add(data);
		}
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
			Tooltip tooltip = new Tooltip("复刻数了"+lowerBound+"至"+higherBound+"的项目个数："+number+"个");
			Tooltip.install(node, tooltip);
		}
	}
	
	
	
	private void initial(RepoDistOverLanguage languageCounts, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<LanguageCount> countIterator = languageCounts.getLanguageCount();
		while (countIterator.hasNext()) {
			LanguageCount languageCount = countIterator.next();
			Language language = languageCount.language;
			int count = languageCount.repositoryCount;
			XYChart.Data<String,Number> data = new XYChart.Data<>(language.getName(), count);
			series.getData().add(data);
		}
		barChart.getData().add(series);
		barChart.setCategoryGap(500.0 / languageCounts.getNumOfLanguages());

		countIterator = languageCounts.getLanguageCount();
		for (int i = 0; i < languageCounts.getNumOfLanguages(); i++) {
			LanguageCount languageCount = countIterator.next();
			int count = languageCount.repositoryCount;
			XYChart.Data<String,Number> data = (Data<String,Number>) series.getData().get(i);
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip("项目个数："+count+"个");
			Tooltip.install(node, tooltip);
		}
	}

	private void initial(UserDistOverCreateTime userCreateOnTimeCounts, String seriesName) {
		XYChart.Series<String,Number> series = new XYChart.Series<>();
		series.setName(seriesName);
		Iterator<UserCreateOnTimeCount> countIterator = userCreateOnTimeCounts.getCounts();
		while (countIterator.hasNext()) {
			UserCreateOnTimeCount languageCount = countIterator.next();
			String createTime = languageCount.time;
			int count = languageCount.count;
			XYChart.Data<String,Number> data = new XYChart.Data<>(createTime, count);
			series.getData().add(data);
		}
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
	}
}
