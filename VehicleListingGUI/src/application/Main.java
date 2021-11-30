package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stg;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		stg = primaryStage;
		primaryStage.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root,800,600);
		primaryStage.setTitle("Vehicle Listings");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void changeScene(String fxml) throws IOException {
		Parent pane = FXMLLoader.load(getClass().getResource(fxml));
		stg.getScene().setRoot(pane);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
