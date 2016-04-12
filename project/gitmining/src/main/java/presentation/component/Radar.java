package presentation.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chart_data.radar.RadarDatas;
import chart_data.radar.RadarDatas.RadarVertex;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class Radar extends AnchorPane{
	/**
	 * 外圈多边形边长
	 */
	private final static int EDGE_LENGTH = 80;
	/**
	 * 文字显示距中心点距离和外圈多边形边长的比例
	 */
	private final static double RADIO = 1.25;
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
	 * 坐标列表
	 */
	private List<Double>	points;
	/**
	 * 文字列表
	 */
	private List<Label> labels;
	
	/**
	 * 雷达图构造函数
	 * @see Radar#Radar(List)
	 * @param marks 分数列表，列表类型为Double型列表（取值范围是[0,1]）
	 * @param headers 周围文字列表，列表类型为String型
	 */
	public Radar(RadarDatas radarDatas) {
		FXMLLoader fxmlLoader = new FXMLLoader(Radar.class.getResource("radar.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.numberOfEdge = radarDatas.getNumOfVertexes();
		this.points = this.getPointPositions();
		this.initial();
		this.setRadarDatas(radarDatas.getVertexes());
	}
	/**
	 * 初始化一些雷达图里面的控件
	 */
	private void initial() {
		this.initialPolygons();
		this.initialLines();
		this.initialLabels();
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
	 * @param points 外圈多边形坐标列表（一个X,一个Y）
	 */
	private void initialPolygons() {
		ObservableList<Double> fatherPolygonPoints = fatherPolygon.getPoints();
		ObservableList<Double> polygonPoints = polygon.getPoints();
		fatherPolygonPoints.clear();
		polygonPoints.clear();
		fatherPolygonPoints.addAll(points);
	}
	
	/**
	 * 初始化内圈边
	 * @param points 外圈多边形坐标列表（一个X,一个Y）
	 */
	private void initialLines() {
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
	 * @param points 外圈多边形坐标列表（一个X,一个Y）
	 */
	private void initialLabels() {
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
	 * @param iterator
	 */
	public void setRadarDatas(Iterator<RadarVertex> iterator){
		ObservableList<Double> polygonPoints = polygon.getPoints();
		Tooltip tooltip = new Tooltip("统计评分指数：");
		for (int i = 0; i < numberOfEdge; i++) {
			RadarVertex radarVertex = iterator.next();
			String header = radarVertex.header;
			double mark = radarVertex.mark;
			labels.get(i).setText(header);
			polygonPoints.add(points.get(i * 2) * mark);
			polygonPoints.add(points.get(i * 2 + 1) * mark);
			tooltip.setText(tooltip.getText()+"\n"+header+"："+String.format("%.2f", mark*100)+"%");
		}
		tooltip.setFont(new Font(18));
		Tooltip.install(this, tooltip);
	}
}
