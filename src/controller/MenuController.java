package controller;

import java.util.ArrayList;

import aplication.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MenuController {
	
	private Main main;

	private ArrayList<String> stationList;
	private ArrayList<String> implementationList;
	
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

    public void setMain(Main main) {
    	this.main = main;     	
    }

}

