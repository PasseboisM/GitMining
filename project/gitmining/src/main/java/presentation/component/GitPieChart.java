package presentation.component;

import java.io.IOException;
import java.util.Iterator;

import chart_data.RepoDistOverLanguage;
import chart_data.RepoDistOverLanguage.LanguageCount;
import chart_data.UserDistOverType;
import chart_data.UserDistOverType.UserTypeCount;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Platform;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

@SuppressWarnings("deprecation")
public class GitPieChart extends PieChart {
	@FXML
	private PieChart pieChart;
	public GitPieChart(UserDistOverType userTypeCounts) {
		this.initialFXML();
		initialLayout();
		this.initial(userTypeCounts);
	}
	
	public GitPieChart(RepoDistOverLanguage repoDistOverLanguage){
		this.initialFXML();
		initialLayout();
		this.initial(repoDistOverLanguage);
	}
	
	private void initialLayout() {
		AnchorPane.setBottomAnchor(this, 25.0);
		AnchorPane.setLeftAnchor(this, 25.0);
		AnchorPane.setRightAnchor(this, 25.0);
		AnchorPane.setTopAnchor(this, 25.0);
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

	private void initial(RepoDistOverLanguage repoDistOverLanguage) {
		ObservableList<PieChart.Data> pieChartData = initialDatas(repoDistOverLanguage);
		this.initialTooltip("项目语言统计图", pieChartData,repoDistOverLanguage);
		this.initialAnimation(pieChartData);
	}

	

	private void initial(UserDistOverType userTypeCounts) {
		ObservableList<PieChart.Data> pieChartData = initialDatas(userTypeCounts);
		this.initialTooltip(UserDistOverType.title, pieChartData,userTypeCounts);
		this.initialAnimation(pieChartData);
	}

	private ObservableList<Data> initialDatas(RepoDistOverLanguage repoDistOverLanguage) {
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		Iterator<LanguageCount> iterator = repoDistOverLanguage.getLanguageCount();
		Double sum = 0.0;
		while(iterator.hasNext()){
			sum+=iterator.next().repositoryCount;
		}
		iterator = repoDistOverLanguage.getLanguageCount();
		while(iterator.hasNext()){
			LanguageCount userTypeCount = iterator.next();
			String type = userTypeCount.language.getName();
			int count = userTypeCount.repositoryCount;
			pieChartData.add(new PieChart.Data(type, count*100.0/sum));
		}
		return pieChartData;
	}

	private ObservableList<PieChart.Data> initialDatas(UserDistOverType userTypeCounts) {
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
		return pieChartData;
	}

	private void initialTooltip(String title, ObservableList<Data> pieChartData,
			RepoDistOverLanguage repoDistOverLanguage) {
		Iterator<LanguageCount> iterator = repoDistOverLanguage.getLanguageCount();
		pieChart.setData(pieChartData);
		pieChart.setLegendSide(Side.RIGHT);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (PieChart.Data data : pieChart.getData()) {
					LanguageCount userTypeCount = iterator.next();
					String type = userTypeCount.language.getName();
					Node node = data.getNode();
					Tooltip tooltip = new Tooltip(type+" : \n"+String.format("%.2f", data.getPieValue()) + "%");
					tooltip.setFont(new Font(18));
					Tooltip.install(node, tooltip);
				}
			}
		});
		pieChart.setTitle(title);
	}
	private void initialTooltip(String title, ObservableList<Data> pieChartData, UserDistOverType userTypeCounts) {
		Iterator<UserTypeCount> iterator = userTypeCounts.getCounts();
		pieChart.setData(pieChartData);
		pieChart.setLegendSide(Side.RIGHT);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for (PieChart.Data data : pieChart.getData()) {
					UserTypeCount userTypeCount = iterator.next();
					String type = userTypeCount.type;
					Node node = data.getNode();
					Tooltip tooltip = new Tooltip(type+" : \n"+String.format("%.2f", data.getPieValue()) + "%");
					tooltip.setFont(new Font(18));
					Tooltip.install(node, tooltip);
				}
			}
		});
		pieChart.setTitle(title);
		
	}



	private void initialAnimation(ObservableList<PieChart.Data> pieChartData) {
		pieChart.setClockwise(false);
		for (PieChart.Data d : pieChartData) {
			d.getNode().setOnMouseEntered(new MouseHoverAnimation(d, pieChart));
			d.getNode().setOnMouseExited(new MouseExitAnimation());
		}
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
//			System.out.println("cos:" + cos);
//			System.out.println("sin" + sin);
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
