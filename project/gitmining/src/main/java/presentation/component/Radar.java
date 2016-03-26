package presentation.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chart_data.ScoreOfRepo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Radar extends AnchorPane{
	/**
	 * 外圈多边形边长
	 */
	private final static int EDGE_LENGTH = 80;
	/**
	 * 文字显示距中心点距离和外圈多边形边长的比例
	 */
	private final static double RADIO = 1.2;
	/**
	 * 多边形边数
	 */
	private Integer numberOfEdge;
	/**
	 * 外圈多边形
	 */
	@FXML private Polygon fatherPolygon;
	/**
	 * 子多边形
	 */
	@FXML private Polygon polygon;
	/**
	 * 容纳外圈多边形、子多边形和内圈边的容器面板
	 */
	@FXML private AnchorPane linesFather;
	/**
	 * 分数列表
	 */
	private List<Double>	marks;
	/**
	 * 文字列表
	 */
	private List<Label> labels;
	
//	/**
//	 * 雷达图构造函数
//	 * @param marks 分数列表，列表类型为Double型列表（取值范围是[0,1]）
//	 */
//	public Radar(List<Double> marks) {
//		FXMLLoader fxmlLoader = new FXMLLoader(Radar.class.getResource("radar.fxml"));
//		fxmlLoader.setController(this);
//		fxmlLoader.setRoot(this);
//		try {
//			fxmlLoader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		this.initialMarks(marks);
//		this.numberOfEdge = marks.size();
//		this.initial();
//	}
	private void initialMarks(List<Double> marks) {
		this.marks = new ArrayList<>();
		for (Double mark : marks) {
			this.marks.add(mark==0.0?0.02:mark);
		}
	}
	/**
	 * 雷达图构造函数
	 * @see Radar#Radar(List)
	 * @param marks 分数列表，列表类型为Double型列表（取值范围是[0,1]）
	 * @param headers 周围文字列表，列表类型为String型
	 */
	public Radar(ScoreOfRepo scoreOfRepo) {
		FXMLLoader fxmlLoader = new FXMLLoader(Radar.class.getResource("radar.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.initialMarks(scoreOfRepo.marks);
		this.numberOfEdge = scoreOfRepo.marks.size();
		this.initial();
		this.setLabelsText(scoreOfRepo.headers);
	}
	/**
	 * 初始化一些雷达图里面的控件
	 */
	private void initial() {
		List<Double> points = this.getPointPositions();
		this.initialPolygons(points);
		this.initialLines(points);
		this.initialLabels(points);
	}
	/**
	 * 获得外圈点的坐标
	 */
	private List<Double> getPointPositions(){
		List<Double> pointsPositions = new ArrayList<>();
		Double arc = Math.PI*2/numberOfEdge;
		for (int i = 0; i < numberOfEdge; i++) {
			pointsPositions.add(Math.cos(arc*i-Math.PI/2)*EDGE_LENGTH);
			pointsPositions.add(Math.sin(arc*i-Math.PI/2)*EDGE_LENGTH);
		}
		return pointsPositions;
	}
	
	/**
	 * 初始化外圈多边形和子多边形
	 * @param points 外圈多变性坐标列表（一个X,一个Y）
	 */
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
	
	/**
	 * 初始化内圈边
	 * @param points 外圈多变性坐标列表（一个X,一个Y）
	 */
	private void initialLines(List<Double> points) {
		for (int i = 0; i < numberOfEdge; i++) {
			Line line = new Line(0, 0, points.get(i*2), points.get(i*2+1));
			line.setLayoutX(83);
			line.setLayoutY(74);
			line.setStroke(Color.web("#b9b9b9",1));
			linesFather.getChildren().add(line);
		}
		
	}
	/**
	 * 初始化标签位置
	 * @param points 外圈多变性坐标列表（一个X,一个Y）
	 */
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
	/**
	 * 设置标签文字
	 * @param headers 标签文字列表
	 */
	public void setLabelsText(List<String> headers){
		for (int i = 0; i < numberOfEdge; i++) {
			labels.get(i).setText(headers.get(i));
		}
	}
}
