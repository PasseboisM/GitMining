package logic.service;

import java.util.Observable;
import java.util.Observer;

import logic.data.MinInfoManager;
import common.message.LoadProgress;

public class Loader extends Observable {

	private static Loader instance = new Loader();
	
	private static volatile LoadProgress progress = null;
	
	private static MinInfoManager manager = null;
	
	public void startLoading(){
		
		System.out.println("Entered loading");
		manager = MinInfoManager.getInstance();
		progress = manager.getProgress();
		Thread inquiringProgress = new Thread(new Runnable(){

			@Override
			public void run() {
				while (!progress.isFinished()) {
						System.out.println("Invoked checker");
						progress = manager.getProgress();
						notifyObservers();
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
	
	@Override
	public void addObserver(Observer o) {
		super.addObserver(o);
		System.out.println("Added "+o);
	}
}
