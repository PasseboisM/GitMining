package presentation.ui.main_ui;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Main_Controller {
	@FXML  
    private Pane MainPanel;  
    @FXML  
    private MenuItem download,refresh,exit,online,offline,guide,member ;  
   

    @FXML  
    private ImageView logo,startIcon; 
    
    //网上主动爬取数据  
    @FXML  
    private void getDownload(ActionEvent event) {   
    		System.out.println("Download!");
       }  
    
     //刷新页面重新启动  
    @FXML  
    private void getRefresh(ActionEvent event) {  
       System.out.println("Refresh!");
       Parent root;
	try {
		root = FXMLLoader.load(getClass().getResource("main.fxml"));
		Scene scene_1 = new Scene(root, 600, 400);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
		  
        
    }  
    
    //退出界面  
    @FXML  
    private void getExit(ActionEvent event) {  
        System.exit(0);  
    }  
      
    //设置为在线浏览模式
    @FXML  
    private void setOnline(ActionEvent event) {  
         System.out.println("Online!");
    }  
      
    
    //设置为离线模式
    @FXML  
    private void setOffline(ActionEvent event) {  
       System.out.println("Offline!");
    }  
      
    
    //一个凑面板的功能
    @FXML  
    private void getGuide(ActionEvent event) {  
    	 JOptionPane.showMessageDialog(
    			 null, "GitMining是一款用于进行数据挖掘和数据分析的软件。" ,"说明",  JOptionPane.PLAIN_MESSAGE);  
    }  
    
    //同上
    @FXML  
    private void getMember(ActionEvent event) {  
    	 JOptionPane.showMessageDialog(
    			 null, "徐江河\n"+"王子安\n"+"孙婧\n"+"徐家逸","鸣谢",  JOptionPane.PLAIN_MESSAGE);  
    }  

    
    //跳转到仓库查询页面
    @FXML  
    private void onStarted(MouseEvent event) {  
    	  System.out.println("start！");
    }  
}
