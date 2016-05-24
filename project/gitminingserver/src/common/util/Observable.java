package common.util;


public interface Observable {

	public void addObserver(Observer observer);
	
	
	public void notifyObservers();

	public void removeObserver(Observer observer);
}
