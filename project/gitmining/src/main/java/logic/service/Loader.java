package logic.service;

import java.util.Observable;

import logic.data.MinInfoManager;
import common.message.LoadProgress;

public class Loader extends Observable {

	private static Loader instance = new Loader();
	
	private static volatile LoadProgress progress = null;
	
	private static MinInfoManager manager = null;
	
	public void startLoading(){
		manager = MinInfoManager.getInstance();
		progress = manager.getProgress();
		Thread inquiringProgress = new Thread(new Runnable(){

			@Override
			public void run() {
				while (!progress.isFinished()) {
					try {
						Thread.sleep(2000);
						progress = manager.getProgress();
						notifyObservers();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		});
		inquiringProgress.start();;
	}
	
	private Loader(){}
	
	public static Loader getInstance() {
		return instance;
	}
	
	public static LoadProgress getProgress() {
		return progress;
	}
}
