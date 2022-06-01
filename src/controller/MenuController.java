package controller;

import java.util.ArrayList;
import aplication.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	void initialize() {
    	implementationList=new ArrayList<String>();
		implementationList.add("Lista Grafo");
		implementationList.add("Matriz Grafo");
		stationList = new ArrayList<String>();
		loadStations();
		originInput.setItems(FXCollections.observableList(stationList));
		destinationInput.setItems(FXCollections.observableList(stationList));
	}
    
    @FXML
    void calculateAllRute(ActionEvent event) {

    }

    @FXML
    void calculateOtherRute(ActionEvent event) {

    }
    
    public void loadStations() {
    	stationList.add("Menga"); 
    	stationList.add("Paso del Comercio"); 
    	stationList.add("Fatima"); 
    	stationList.add("La Americas"); 
    	stationList.add("San Pedro"); 
    	stationList.add("Torre de Cali");
    	stationList.add("La Ermita");
    	stationList.add("Fray Damian");
    	stationList.add("Atanasio Girardot");
    	stationList.add("Chapinero");
    	stationList.add("Andres Sanin");
    	stationList.add("Primitivo");
    	stationList.add("Santa Librada");
    	stationList.add("Estadio");
    	stationList.add("Tequendama");
    	stationList.add("Plaza de Toros");
    	stationList.add("Calipso");
    	stationList.add("Simon Bolivar");
    	stationList.add("Universidades");
    	stationList.add("Unidad Deportiva");
    	stationList.add("Refugio");
    	stationList.add("Capri");
    	stationList.add("Pampalinda");
    	stationList.add("Lido");
    	stationList.add("Melendez");
    	stationList.add("Univalle");
    	stationList.add("Buitrera");
    	stationList.add("Villanueva");
    	stationList.add("San Bosco");
    	stationList.add("Siete de Agosto");
    	stationList.add("Alamos");
    	stationList.add("Centro");
    	stationList.add("Flora Industrial");
    	stationList.add("Salomia");
    	stationList.add("Amanecer");
    	stationList.add("Caldas");
    	stationList.add("Chiminangos");
    	stationList.add("Vipasa");
    	stationList.add("Versalles");
    	stationList.add("Prados del Norte");
    	stationList.add("Manzanares");
    	stationList.add("Rio Cali");
    	stationList.add("Piloto");
    	stationList.add("San Nicolas");
    	stationList.add("San Pedro");
    	stationList.add("Petecui");
    	stationList.add("Sucre");
    	stationList.add("Manzana del Saber");
    	stationList.add("Cañaveralejo");
    	stationList.add("Nuevo Latir");
    	stationList.add("El Pondaje");
    	stationList.add("El Trevol");
    	stationList.add("Villa Colombia");
    	stationList.add("Ulpiano Lloreda");
    	stationList.add("San Pascual");
    	stationList.add("Santa Rosa");
    	stationList.add("Plaza Caycedo");
    	stationList.add("Cien palos");
    	stationList.add("Conquistadores");
    }

	public void drawPoint(int stationIndex) {
		Station station = main.getBack().getStation(stationIndex);
		System.out.println(station.getName());
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
	
	public void setMain(Main main) {
		this.main = main;
		gc = canvasMarks.getGraphicsContext2D();
		drawTravel(0, 4);
		drawTravel(4, 5);
		drawTravel(5, 14);
		drawTravel(14, 23);
	}
}

