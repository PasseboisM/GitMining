package presentation.component;

import java.io.IOException;
import java.util.Iterator;

import chart_data.RepoDistOverCreateTime;
import chart_data.RepoDistOverCreateTime.RepoCreateOnTimeCount;
import chart_data.UserDistOverType;
import chart_data.UserDistOverType.UserTypeCount;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.util.Duration;

@SuppressWarnings("deprecation")
public class GitPieChart extends PieChart {
	@FXML
	private PieChart pieChart;
	public GitPieChart(UserDistOverType userTypeCounts) {
		this.initialFXML();
		this.initial(userTypeCounts);
	}
	
	public GitPieChart(RepoDistOverCreateTime repoCreateOnTimeCounts) {
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

	private void initial(RepoDistOverCreateTime repoCreateOnTimeCounts) {
		ObservableList<PieChart.Data> pieChartData = initialDatas(repoCreateOnTimeCounts);
		this.initialTooltip(repoCreateOnTimeCounts.title, pieChartData);
		pieChart.setClockwise(false);
		for (PieChart.Data d : pieChartData) {
			d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, pieChart));
			d.getNode().setOnMouseExited(new MouseExitAnimation());
			}
	}

	private ObservableList<PieChart.Data> initialDatas(RepoDistOverCreateTime repoCreateOnTimeCounts) {
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		Iterator<RepoCreateOnTimeCount> iterator = repoCreateOnTimeCounts.getCounts();
		Double sum = 0.0;
		while(iterator.hasNext()){
			sum+=iterator.next().count;
		}
		iterator = repoCreateOnTimeCounts.getCounts();
		while(iterator.hasNext()){
			RepoCreateOnTimeCount repoCreateOnTimeCount = iterator.next();
			String type = repoCreateOnTimeCount.timeLo+"~"+repoCreateOnTimeCount.timeHi;
			int count = repoCreateOnTimeCount.count;
			pieChartData.add(new PieChart.Data(type, count*100.0/sum));
		}
		return pieChartData;
	}

	private void initial(UserDistOverType userTypeCounts) {
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
		this.initialTooltip(UserDistOverType.title, pieChartData);
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
	
	

	static class MouseHoverAnimation implements EventHandler<MouseEvent> {
		static final Duration ANIMATION_DURATION = new Duration(500);
		static final double ANIMATION_DISTANCE = 0.03;
		private double cos;
		private double sin;
		private PieChart chart;

		public MouseHoverAnimation(PieChart.Data d, PieChart chart) {
			this.chart = chart;
			double start = 0;
			double angle = calcAngle(d);
			for (PieChart.Data tmp : chart.getData()) {
				if (tmp == d) {
					break;
				}
				start += calcAngle(tmp);
			}

			cos = Math.cos(Math.toRadians(start + angle / 2));
			sin = Math.sin(Math.toRadians(start + angle / 2));
		}

		@Override
		public void handle(MouseEvent arg0) {
			Node n = (Node) arg0.getSource();
			double minX = Double.MAX_VALUE;
			double maxX = Double.MAX_VALUE * -1;

			for (PieChart.Data d : chart.getData()) {
				minX = Math.min(minX, d.getNode().getBoundsInParent().getMinX());
				maxX = Math.max(maxX, d.getNode().getBoundsInParent().getMaxX());
			}

			double radius = maxX - minX;
			System.out.println("cos:" + cos);
			System.out.println("sin" + sin);
			TranslateTransitionBuilder.create().toX((radius * ANIMATION_DISTANCE) * cos)
					.toY((radius * ANIMATION_DISTANCE) * (-sin)).duration(ANIMATION_DURATION).node(n).build().play();
		}

		private static double calcAngle(PieChart.Data d) {
			double total = 0;
			for (PieChart.Data tmp : d.getChart().getData()) {
				total += tmp.getPieValue();
			}
			return 360 * (d.getPieValue() / total);
		}
	}

	static class MouseExitAnimation implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			TranslateTransitionBuilder.create().toX(0).toY(0).duration(new Duration(500)).node((Node) event.getSource())
					.build().play();
		}
	}

}
