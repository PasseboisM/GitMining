package presentation.component;
import java.util.Iterator;

import chart_data.UserDistOverCreateTime;
import chart_data.UserDistOverCreateTime.UserCreateOnTimeCount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * A custom candlestick chart.
 *
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.NumberAxis
 * @see javafx.scene.chart.XYChart
 */
public class GitParetoChartController {

    // DAY, OPEN, CLOSE, HIGH, LOW, AVERAGE
     
     private void initialLayout(Node rootUINode) {
 		AnchorPane.setBottomAnchor(rootUINode, 0.0);
 		AnchorPane.setLeftAnchor(rootUINode, 0.0);
 		AnchorPane.setRightAnchor(rootUINode, 0.0);
 		AnchorPane.setTopAnchor(rootUINode, 0.0);
 	}
     

    @SuppressWarnings("unchecked")
	public GitParetoChart createChart(UserDistOverCreateTime userDistOverCreateTime) {
    	final CategoryAxis xAxis = new CategoryAxis();
    	xAxis.setTickLabelGap(1);
        final NumberAxis yAxis = new NumberAxis();
        final GitParetoChart bc = new GitParetoChart(xAxis,yAxis);
        // setup chart
        bc.setTitle(UserDistOverCreateTime.title);
        xAxis.setLabel(UserDistOverCreateTime.xAxisLabel);
        yAxis.setLabel(UserDistOverCreateTime.yAxisLabel);
        // add starting data
        Iterator<UserCreateOnTimeCount> itr = userDistOverCreateTime.getCounts();
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        int sum=0;
        while(itr.hasNext()){
        	UserCreateOnTimeCount userCreateOnTimeCount = itr.next();
        	int count = userCreateOnTimeCount.count;
        	sum+=count;
        	String label = userCreateOnTimeCount.timeLo+"~"+userCreateOnTimeCount.timeHi;
        	series.getData().add(
        			new XYChart.Data<String,Number>(label,count,new GitParetoExtraValues(0, sum, 0, sum))
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

    /*private class ParetoData{
    	private String date;
    	private int actual;
    	private int percent;
    	
		public ParetoData(String date, int actual, int percent) {
			this.date = date;
			this.actual = actual;
			this.percent = percent;
		}
    }*/
}
