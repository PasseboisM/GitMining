package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Pie extends AnchorPane {
	@FXML
	private PieChart pie;
	
	//饼图外的label
	private List<Label> labels;
	
	//饼图的数据
	private List<Double> datas;
	
	public Pie(List<Double> datas) {
		FXMLLoader fxmlLoader = new FXMLLoader(Pie.class.getResource("pie.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.datas = datas;
		this.initial();
	}

	
	public Pie(List<Double> datas,List<String> labels) {
		this(datas);
		this.setLabelsText(labels);
	}
	
	
	
	/**
	 * 初始化横轴上标签
	 */
	private void setLabelsText(List<String> languages2) {
		for(int i=0;i<labels.size();i++){
			labels.get(i).setText(languages2.get(i));
		}
	}

	private void initial() {
		// TODO Auto-generated method stub
		
	}

}
