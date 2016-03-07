package presentation.ui.main_ui;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;  
import javafx.scene.Parent;  
import javafx.scene.Scene;

import javafx.stage.Stage;  
import javafx.stage.StageStyle;
import presentation.textui.MainApp;  

public class Main_Start extends Application{

		 public static void main(String[] args) {  
		        Application.launch(MainApp.class, args);  
		    }  
		
		
		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));  
			  
	        Scene scene = new Scene(root, 600, 400);  
	        stage.initStyle(StageStyle.DECORATED);  
	        stage.setScene(scene);  
	        stage.setTitle("GitMining");  
	        stage.show();  
	        stage.setResizable(false);


		}

	}