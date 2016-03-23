package presentation.component;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Pie extends AnchorPane {
	//饼图外的label
	private List<Label> labels;
	
	//饼图的数据
	private List<Double> datas;
	
	public Pie(List<Double> datas) {
		FXMLLoader fxmlLoader = new FXMLLoader(Bar.class.getResource("pie.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.initial();
	}

	private void initial() {
		// TODO Auto-generated method stub
		
	}

}
