package aplication;


import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/MenuView.fxml"));
			BorderPane root = (BorderPane) loader.load();
			MenuController controller = loader.getController();
			back = new TransportationSystem(controller);
			controller.setMain(this);
			Scene scene = new Scene(root, 1200, 720);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("file:files/mioLogo.png"));
			currentStage = primaryStage;
			currentStage.setTitle("Rutas Mío");
			currentStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public TransportationSystem getBack() {
		return back;
	}
}
