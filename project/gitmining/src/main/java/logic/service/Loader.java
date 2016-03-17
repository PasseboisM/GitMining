package logic.service;

import common.message.LoadProgress;
import common.util.Observable;
import common.util.Observer;
import logic.data.MinInfoManager;

public class Loader implements Observable {

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
	
	@Override
	public void addObserver(Observer observer) {
		MinInfoManager.getInstance().addObserver(observer);
	}

	@Override
	public void notifyObservers() {
		//本类为伪Observable，会将Observer委托给MinInfoManager，所以不用实现此方法	
	}

	@Override
	public void removeObserver(Observer observer) {
		manager.removeObserver(observer);		
	}

}
