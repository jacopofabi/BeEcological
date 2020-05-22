package logic;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import logic.utilities.PageLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class StandaloneMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	
		try {
			primaryStage.setTitle("BeEcological - Homepage");
			URL url = new File("src/res/fxml/Homepage.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
	        Scene scene = new Scene(root, 1366, 768);
	        primaryStage.setScene(scene);
	        primaryStage.setMaximized(true);
	        primaryStage.show();
		}catch(Exception e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}    
	}
	
	public static void main(String[] args) throws SQLException {
			launch(args);
	}
}
