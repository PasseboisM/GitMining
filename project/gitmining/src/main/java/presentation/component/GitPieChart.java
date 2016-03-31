package presentation.component;

import java.io.IOException;
import java.util.Iterator;

import chart_data.RepoCreateOnTimeCounts;
import chart_data.RepoCreateOnTimeCounts.RepoCreateOnTimeCount;
import chart_data.UserTypeCounts;
import chart_data.UserTypeCounts.UserTypeCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class GitPieChart extends PieChart {
	@FXML
	private PieChart pieChart;
	public GitPieChart(UserTypeCounts userTypeCounts) {
		this.initialFXML();
		this.initial(userTypeCounts);
	}
	
	public GitPieChart(RepoCreateOnTimeCounts repoCreateOnTimeCounts) {
		this.initialFXML();
		this.initial(repoCreateOnTimeCounts);
	}

	private void initialFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(GitPieChart.class.getResource("pieChart.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initial(RepoCreateOnTimeCounts repoCreateOnTimeCounts) {
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		Iterator<RepoCreateOnTimeCount> iterator = repoCreateOnTimeCounts.getCounts();
		Double sum = 0.0;
		while(iterator.hasNext()){
			sum+=iterator.next().count;
		}
		iterator = repoCreateOnTimeCounts.getCounts();
		while(iterator.hasNext()){
			RepoCreateOnTimeCount repoCreateOnTimeCount = iterator.next();
			String type = repoCreateOnTimeCount.time;
			int count = repoCreateOnTimeCount.count;
			pieChartData.add(new PieChart.Data(type, count*100.0/sum));
		}
		this.initialTooltip(repoCreateOnTimeCounts.title, pieChartData);
	}

	private void initial(UserTypeCounts userTypeCounts) {
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		Iterator<UserTypeCount> iterator = userTypeCounts.getCounts();
		Double sum = 0.0;
		while(iterator.hasNext()){
			sum+=iterator.next().count;
		}
		iterator = userTypeCounts.getCounts();
		while(iterator.hasNext()){
			UserTypeCount userTypeCount = iterator.next();
			String type = userTypeCount.type;
			int count = userTypeCount.count;
			pieChartData.add(new PieChart.Data(type, count*100.0/sum));
		}
		this.initialTooltip(userTypeCounts.title, pieChartData);
	}


	private void initialTooltip(String title, ObservableList<PieChart.Data> pieChartData) {
		pieChart.setData(pieChartData);
		pieChart.setLegendSide(Side.RIGHT);
		for (PieChart.Data data : pieChart.getData()) {
			Node node = data.getNode();
			Tooltip tooltip = new Tooltip(String.format("%.2f", data.getPieValue()) + "%");
			tooltip.setFont(new Font(25));
			Tooltip.install(node, tooltip);
		}
		pieChart.setTitle(title);
		
	}

}
