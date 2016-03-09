package logic.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import logic.data.MinInfoManager;
import common.message.LoadProgress;
import common.util.Observer;

public class Loader {

	private static Loader instance = new Loader();
	
	private static volatile LoadProgress progress = null;
	
	private static MinInfoManager manager = null;
	
	public void startLoading(){
		
		manager = MinInfoManager.getInstance();
		progress = manager.getProgress();
		System.out.println(progress);

	}
	
	private Loader(){}
	
	public static Loader getInstance() {
		return instance;
	}
	
	public static LoadProgress getProgress() {
		return MinInfoManager.getInstance().getProgress();
	}
	
	public void addObserver(Observer observer) {
		MinInfoManager.getInstance().addObserver(observer);
	}

}
