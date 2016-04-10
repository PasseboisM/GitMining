package presentation.component;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class WaitLoader extends AnchorPane{
	public WaitLoader(String text){
		FXMLLoader fxmlLoader = new FXMLLoader(WaitLoader.class.getResource("waitLoader.fxml"));
		fxmlLoader.setController(this);
		fxmlLoader.setRoot(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public WaitLoader(){
		this("  数据加载中...");
	}
}
