package presentation.component;
import java.util.Iterator;

import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A custom candlestick chart.
 *
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.NumberAxis
 * @see javafx.scene.chart.XYChart
 */
public class GitParetoChartController extends Application {

    // DAY, OPEN, CLOSE, HIGH, LOW, AVERAGE
//	private ParetoData data = 
//	private List<ParetoData> data =Arrays.asList(new ParetoData("1", 50.0, 20.0),
//			new ParetoData("2", 26.0, 25.0),new ParetoData("3", 30.0, 32.0));
//    private double[][] data = new double[][]{
//    	{1,  25, 20, 32, 16, 20},
//        {2,  26, 30, 33, 22, 25},
//        {3,  30, 38, 40, 20, 32},
//        {4,  24, 30, 34, 22, 30},
//        {5,  26, 36, 40, 24, 32},
//        {6,  28, 38, 45, 25, 34},
//        {7,  36, 30, 44, 28, 39},
//        {8,  30, 18, 36, 16, 31},
//        {9,  40, 50, 52, 36, 41},
//        {10, 30, 34, 38, 28, 36},
//        {11, 24, 12, 30, 8,  32.4},
//        {12, 28, 40, 46, 25, 31.6},
//        {13, 28, 18, 36, 14, 32.6},
//        {14, 38, 30, 40, 26, 30.6},
//        {15, 28, 33, 40, 28, 30.6},
//        {16, 25, 10, 32, 6,  30.1},
//        {17, 26, 30, 42, 18, 27.3},
//        {18, 20, 18, 30, 10, 21.9},
//        {19, 20, 10, 30, 5,  21.9},
//        {20, 26, 16, 32, 10, 17.9},
//        {21, 38, 40, 44, 32, 18.9},
//        {22, 26, 40, 41, 12, 18.9},
//        {23, 30, 18, 34, 10, 18.9},
//        {24, 12, 23, 26, 12, 18.2},
//        {25, 30, 40, 45, 16, 18.9},
//        {26, 25, 35, 38, 20, 21.4},
//        {27, 24, 12, 30, 8,  19.6},
//        {28, 23, 44, 46, 15, 22.2},
//        {29, 28, 18, 30, 12, 23},
//        {30, 28, 18, 30, 12, 23.2},
//        {31, 28, 18, 30, 12, 22}
//    };

    private void init(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
         root.getStylesheets().add(getClass().getResource("mixChart.css").toExternalForm());
         primaryStage.setScene(new Scene(root));
         UserDistOverCreateTime userDistOverCreateTime = new UserDistOverCreateTime();
         userDistOverCreateTime.addCreateCount("1", "2", 2);
         userDistOverCreateTime.addCreateCount("2", "3", 500);
         userDistOverCreateTime.addCreateCount("3", "4", 1500);
         userDistOverCreateTime.addCreateCount("4", "5", 10000);
         userDistOverCreateTime.addCreateCount("5", "6", 5000);
         userDistOverCreateTime.addCreateCount("6", "7", 2000);
         userDistOverCreateTime.addCreateCount("7", "8", 1000);
         userDistOverCreateTime.addCreateCount("8", "9", 25);
         GitParetoChart chart = createChart(userDistOverCreateTime);
         initialLayout(chart);
         root.getChildren().add(chart);
     }
     
     private void initialLayout(Node rootUINode) {
 		AnchorPane.setBottomAnchor(rootUINode, 0.0);
 		AnchorPane.setLeftAnchor(rootUINode, 0.0);
 		AnchorPane.setRightAnchor(rootUINode, 0.0);
 		AnchorPane.setTopAnchor(rootUINode, 0.0);
 	}
     

    @SuppressWarnings("unchecked")
	public GitParetoChart createChart(UserDistOverCreateTime userDistOverCreateTime) {
    	final CategoryAxis xAxis = new CategoryAxis();
//    	xAxis.setTickLabelGap(1);
        final NumberAxis yAxis = new NumberAxis();
        final GitParetoChart bc = new GitParetoChart(xAxis,yAxis);
        // setup chart
        bc.setTitle(UserDistOverCreateTime.title);
        xAxis.setLabel(UserDistOverCreateTime.xAxisLabel);
        yAxis.setLabel(UserDistOverCreateTime.yAxisLabel);
        // add starting data
        Iterator<UserCreateOnTimeCount> itr = userDistOverCreateTime.getCounts();
//        int num = userDistOverCreateTime.getNumOfCount();
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        int sum=0;
        while(itr.hasNext()){
        	UserCreateOnTimeCount userCreateOnTimeCount = itr.next();
        	String label = userCreateOnTimeCount.timeLo+"~"+userCreateOnTimeCount.timeHi;
        	int count = userCreateOnTimeCount.count;
        	sum+=count;
        	series.getData().add(
           		 new XYChart.Data<String,Number>(label,count,new GitParetoExtraValues(0,count,0,sum/5.0))
           );
        }
        
        ObservableList<XYChart.Series<String,Number>> data = bc.getData();
        if (data == null) {
            data = FXCollections.observableArrayList(series);
            bc.setData(data);
        } else {
            bc.getData().add(series);
        }
        bc.getStylesheets().add(getClass().getResource("mixChart.css").toExternalForm());
        initialLayout(bc);
        return bc;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}
