package aplication;


import controller.MenuController;
import dataStructures.IGraph;
import dataStructures.ListGraph;
import dataStructures.MatrixGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.TransportationSystem;

public class Main extends Application {
	
	private TransportationSystem back;
	
	public static void main(String[] args) {
		launch(args);
	}
	private Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		back = new TransportationSystem();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/MenuView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MenuController controller = loader.getController();
			controller.setMain(this);
			Scene scene = new Scene(root, 850, 720);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			primaryStage.setScene(scene);
			currentStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public TransportationSystem getBack() {
		return back;
	}
	
}
