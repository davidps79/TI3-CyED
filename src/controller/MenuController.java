package controller;

import java.util.ArrayList;
import aplication.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import model.Station;

public class MenuController {
	
	private Main main;

	private ArrayList<String> stationList;
	private ArrayList<String> implementationList;
	private int[] mainStations = {0, 1, 10, 16, 17, 18, 48};

	@FXML
	private Canvas canvasMarks;
	
	private GraphicsContext gc;
	
    @FXML
    private Button calculateAllRuteInput;

    @FXML
    private Button calculateOtherRuteInput;

    @FXML
    private ComboBox<String> destinationInput;

    @FXML
    private ComboBox<String> implementationInput;

    @FXML
    private ComboBox<String> originInput;
    
    @FXML
    private TextArea routeOutput;
    
    @FXML
	void initialize() {
    	implementationList = new ArrayList<String>();
		implementationList.add("Lista de ady.");
		implementationList.add("Matriz de ady.");
		implementationInput.setItems(FXCollections.observableList(implementationList));
		implementationInput.setValue(implementationList.get(0));
		routeOutput.setFocusTraversable(false);
	}
    
	public void setMain(Main main) {
		this.main = main;
		
		stationList = main.getBack().getStationList();
		originInput.setItems(FXCollections.observableList(stationList));
		destinationInput.setItems(FXCollections.observableList(stationList));
		
		gc = canvasMarks.getGraphicsContext2D();
	}
    
    @FXML
    void calculateAllRoutes() {
    	gc.clearRect(0, 0, 550, 720);
    	routeOutput.clear();
    	main.getBack().calculateAllRoutes(implementationInput.getValue());
    }

    @FXML
    void calculateRoute() {
    	gc.clearRect(0, 0, 550, 720);
    	routeOutput.clear();
    	String from = originInput.getValue();
    	String to = destinationInput.getValue();
    	
    	if (from == null || to == null) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Campos vacíos");
    		alert.setHeaderText(null);
    		alert.setContentText("Debes indicar un punto de origen y un punto de destino");
    		alert.showAndWait();
    	} else {
    		main.getBack().calculateRoute(from, to, implementationInput.getValue());
    		originInput.setValue(null);
    		destinationInput.setValue(null);
    	}
    }

	public void drawPoint(int stationIndex) {
		Station station = main.getBack().getStation(stationIndex);
		//System.out.println(station.getName());
		float x1 = station.getX();
		float y1 = station.getY();
		
		int radius = 3;
		
		for (int i=0; i<mainStations.length; i++) {
			if (stationIndex == mainStations[i]) {
				radius = 8;
				break;
			}
		}
		
		gc.fillOval(x1, y1, radius*2, radius*2);
	}
	
	public void addRoute(int index) {
		routeOutput.setText(main.getBack().getStation(index).getName() + "\n" + routeOutput.getText());
	}
	
	public void drawEdge(int from, int to) {
		Station fromStation = main.getBack().getStation(from);
		Station toStation = main.getBack().getStation(to);
		
		int radius = 3;
		
		for (int i=0; i<mainStations.length; i++) {
			if (from == mainStations[i]) {
				radius = 8;
				break;
			}
		}
		
		float x1 = fromStation.getX() + radius;
		float y1 = fromStation.getY() + radius;
		
		radius = 3;
		
		for (int i=0; i<mainStations.length; i++) {
			if (to == mainStations[i]) {
				radius = 8;
				break;
			}
		}
		
		float x2 = toStation.getX() + radius;
		float y2 = toStation.getY() + radius;
		
		gc.strokeLine(x1, y1, x2, y2);
	}
	
	public void drawTravel(int from, int to) {
		drawPoint(from);
		drawEdge(from, to);
		drawPoint(to);
	}
}

