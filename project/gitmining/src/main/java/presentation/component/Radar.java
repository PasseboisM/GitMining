package presentation.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Radar extends AnchorPane{
	private final static int EDGE_LENGTH = 80;
	private final static double RADIO = 1.2;
	private Integer numberOfEdge = 6;
	@FXML private Polygon fatherPolygon;
	@FXML private Polygon polygon;
	@FXML private AnchorPane linesFather;
	private List<Double>	marks;
	private List<Label> labels;
	
	
	public Radar(List<Double> marks) {
		FXMLLoader fxmlLoader = new FXMLLoader(Radar.class.getResource("radar.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.marks = marks;
		this.numberOfEdge = marks.size();
		this.initial();
	}
	
	public Radar(List<Double> marks,List<String> headers) {
		this(marks);
		this.setText(headers);
	}

	private void initial() {
		List<Double> points = this.getPointPositions();
		this.initialPolygons(points);
		this.initialLines(points);
		this.initialLabels(points);
	}

	private void initialLabels(List<Double> points) {
		labels = new ArrayList<>();
		for (int i = 0; i < numberOfEdge; i++) {
			Label label = new Label();
			label.setLayoutX(143+points.get(i*2)*RADIO-8);
			label.setLayoutY(127+points.get(i*2+1)*RADIO-8);
			labels.add(label);
			this.getChildren().add(label);
		}
	}

	private void initialLines(List<Double> points) {
		for (int i = 0; i < numberOfEdge; i++) {
			Line line = new Line(0, 0, points.get(i*2), points.get(i*2+1));
			line.setLayoutX(83);
			line.setLayoutY(74);
			line.setStroke(Color.web("#b9b9b9",1));
			linesFather.getChildren().add(line);
		}
		
	}

	private void initialPolygons(List<Double> points) {
		ObservableList<Double> fatherPolygonPoints = fatherPolygon.getPoints();
		ObservableList<Double> polygonPoints = polygon.getPoints();
		fatherPolygonPoints.clear();
		polygonPoints.clear();
		fatherPolygonPoints.addAll(points);
		for (int i = 0; i < numberOfEdge; i++) {
			polygonPoints.add(points.get(i * 2) * marks.get(i));
			polygonPoints.add(points.get(i * 2 + 1) * marks.get(i));
		}
	}
	
	private List<Double> getPointPositions(){
		List<Double> pointsPositions = new ArrayList<>();
		Double arc = Math.PI*2/numberOfEdge;
		for (int i = 0; i < numberOfEdge; i++) {
			pointsPositions.add(Math.cos(arc*i)*EDGE_LENGTH);
			pointsPositions.add(Math.sin(arc*i)*EDGE_LENGTH);
		}
		return pointsPositions;
	}
	
	public void setText(List<String> headers){
		for (int i = 0; i < numberOfEdge; i++) {
			labels.get(i).setText(headers.get(i));
		}
	}
}
