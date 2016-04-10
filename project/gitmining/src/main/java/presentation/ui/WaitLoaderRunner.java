package presentation.ui;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import presentation.component.WaitLoader;
import presentation.ui.statistics.StatisticsPane;

public class WaitLoaderRunner implements Runnable{

	private AnchorPane rightComponentParent;
	private WaitLoader waitLoader;
	private StatisticsPane statisticsPane;
	
	public WaitLoaderRunner(AnchorPane rightComponentParent, WaitLoader waitLoader, StatisticsPane statisticsPane) {
		this.rightComponentParent = rightComponentParent;
		this.waitLoader = waitLoader;
		this.statisticsPane = statisticsPane;
	}

	@Override
	public void run() {
		AnchorPane anchorPane = statisticsPane.getInstance(rightComponentParent);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				rightComponentParent.getChildren().add(anchorPane);
				rightComponentParent.getChildren().remove(waitLoader);
			}
		});
	}

}
