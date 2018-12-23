package application;
	
import org.weather.util.WeatherUtilManager;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("../org/weather/view/MainApplication.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../org/weather/resources/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//load all properties during application launch
			WeatherUtilManager.getInstance().loadProperties();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
