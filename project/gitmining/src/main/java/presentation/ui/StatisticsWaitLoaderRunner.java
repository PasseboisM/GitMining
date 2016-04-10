package presentation.ui;

import common.service.GitUser;
import common.service.Repository;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import presentation.component.WaitLoader;
import presentation.ui.statistics.StatisticsPane;

public class StatisticsWaitLoaderRunner implements Runnable{

	private AnchorPane rightComponentParent;
	private WaitLoader waitLoader;
	private StatisticsPane statisticsPane;
	private GitUser gitUser;
	private Repository repository;
	
	public StatisticsWaitLoaderRunner(AnchorPane rightComponentParent, WaitLoader waitLoader, StatisticsPane statisticsPane) {
		this.rightComponentParent = rightComponentParent;
		this.waitLoader = waitLoader;
		this.statisticsPane = statisticsPane;
	}
	
	public StatisticsWaitLoaderRunner(AnchorPane rightComponentParent, GitUser gitUser,WaitLoader waitLoader, StatisticsPane statisticsPane) {
		this.rightComponentParent = rightComponentParent;
		this.waitLoader = waitLoader;
		this.statisticsPane = statisticsPane;
	}
	public StatisticsWaitLoaderRunner(AnchorPane rightComponentParent, Repository repository,WaitLoader waitLoader, StatisticsPane statisticsPane) {
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
