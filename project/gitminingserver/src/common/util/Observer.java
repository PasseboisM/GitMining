package common.util;

public interface Observer {

	public void update();
	
	public void update(Observable observable,Object obj);
	
}
