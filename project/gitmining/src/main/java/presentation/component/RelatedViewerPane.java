package presentation.component;

import common.service.Repository;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RelatedViewerPane extends AnchorPane{
	private Label fullName;
	public RelatedViewerPane(AnchorPane rightComponentParent,Repository repository) {
		fullName = new Label(repository.getFull_name());
		this.setOnMouseClicked(value->{
//			rightComponentParent.getChildren().add(RepoDetailsController.getInstance(rightComponentParent, repository));
			System.out.println(repository.getFull_name());
		});
		this.getChildren().add(fullName);
	}
}
