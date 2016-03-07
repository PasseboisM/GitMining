package presentation.textui;



import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MainController {
	 	@FXML  
	    private Pane MainPanel;  
	    @FXML  
	    private MenuItem download,refresh,exit,online,offline,guide,member ;  
	    @FXML
	    private Button startIcon;  

	    @FXML  
	    private ImageView imageOne; 
	      
	    @FXML  
	    private void getDownload(ActionEvent event) {   
	  
	       }  
	       
	    @FXML  
	    private void getRefresh(ActionEvent event) {  
	       
	    }  
	      
	    @FXML  
	    private void getExit(ActionEvent event) {  
	        System.exit(0);  
	    }  
	      
	    @FXML  
	    private void setOnline(ActionEvent event) {  
	         
	    }  
	      
	    @FXML  
	    private void setOffline(ActionEvent event) {  
	       
	    }  
	      
	    @FXML  
	    private void getGuide(ActionEvent event) {  
	    	 JOptionPane.showMessageDialog(
	    			 null, "GitMining是一款用于进行数据挖掘和数据分析的软件。" ,"说明",  JOptionPane.PLAIN_MESSAGE);  
	    }  
	    @FXML  
	    private void getMember(ActionEvent event) {  
	    	 JOptionPane.showMessageDialog(
	    			 null, "徐江河\n"+"王子安\n"+"孙婧\n"+"徐家逸","鸣谢",  JOptionPane.PLAIN_MESSAGE);  
	    }  
	    @FXML  
	    private void onStart(ActionEvent event) {  
	    	 
	    }  
	    @FXML  
	    private void onOne(MouseEvent event) {  
	    	  System.out.println("1");
	    }  
}
