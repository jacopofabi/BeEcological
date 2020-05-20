package logic;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class StandaloneMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
	
		try {
			primaryStage.setTitle("Homepage");
			URL url = new File("src/res/fxml/Homepage.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
	        Scene scene = new Scene(root, 1366, 768);
	        primaryStage.setScene(scene);
	        primaryStage.setMaximized(true);
	        primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}    
	}
	
	public static void main(String[] args) throws SQLException {
			launch(args);
	}
}
