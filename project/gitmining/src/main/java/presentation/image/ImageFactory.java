package presentation.image;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

public class ImageFactory {
	public static Image getImageByFileName(String filename) throws MalformedURLException{
		File file = new File("resouces/images/"+filename);
		Image image = new Image(file.toURI().toURL().toString(),true);
		return image;
	}
}
