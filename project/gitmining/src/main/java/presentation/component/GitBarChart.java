package presentation.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import chart_data.LanguageCounts;
import chart_data.LanguageCounts.LanguageCount;
import chart_data.UserCreateOnTimeCounts;
import chart_data.UserCreateOnTimeCounts.UserCreateOnTimeCount;
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
	
	public GitBarChart(LanguageCounts languageCounts) {
		this.initialFXML();
		this.initialText("项目使用语言统计图", "语言", "项目个数");
		this.initial(languageCounts, "项目");
	}
	
	public GitBarChart(UserCreateOnTimeCounts userCreateOnTimeCounts) {
		this.initialFXML();
		this.initialText("用户创建时间统计图", "创建时间", "用户个数");
		this.initial(userCreateOnTimeCounts, "用户");
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
	
	
	
	private void initial(LanguageCounts languageCounts, String seriesName) {
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
			Tooltip tooltip = new Tooltip(String.valueOf("项目个数："+count+"个"));
			Tooltip.install(node, tooltip);
		}
	}

	private void initial(UserCreateOnTimeCounts userCreateOnTimeCounts, String seriesName) {
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
			Tooltip tooltip = new Tooltip(String.valueOf("用户个数："+count+"个"));
			Tooltip.install(node, tooltip);
		}
	}
}
