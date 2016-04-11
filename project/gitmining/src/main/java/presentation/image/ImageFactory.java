package presentation.image;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

public class ImageFactory {
	public static final String SEARCH_BACKGROUND = "searchBackground_2.jpg";
	public static final String BACK = "10.png";
	public static final String STATISTICS_REPO_CREATE_TIME = "Background.jpg";
	public static final String LOADING_BACKGROUND = "userSearchBackground_2.jpg";
	public static final String GIT_LOGO = "icon.png";
	public static Image getImageByFileName(String filename) throws MalformedURLException{
		File file = new File("resources/images/"+filename);
		Image image = new Image(file.toURI().toURL().toString(),true);
		return image;
	}
}
