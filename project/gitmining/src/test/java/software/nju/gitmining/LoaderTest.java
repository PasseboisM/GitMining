package software.nju.gitmining;

import static org.junit.Assert.*;
import logic.data.MinInfoManager;
import logic.service.Loader;

import org.junit.Test;

import common.message.LoadProgress;
import common.util.Observable;
import common.util.Observer;

public class LoaderTest implements Observer {

	@Test
	public void test() {
		MinInfoManager.getInstance().addObserver(this);
		Loader.getInstance().startLoading();
		while(true) {
			try {
				Thread.sleep(500);
				System.out.println("Waiting");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {
		LoadProgress progress =  MinInfoManager.getInstance().getProgress();
		if(progress.getLoadedRepoNum()>200) {
			System.out.println("Got 200!!!!!!!!!!!!!!!!!!!!!!!");
		}
		
	}

	@Override
	public void update(Observable observable, Object obj) {
		// TODO Auto-generated method stub
		
	}



}
