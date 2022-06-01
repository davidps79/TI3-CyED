package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TransportationSystem {
	private final int stationNumber = 59;
	private Station[] stations;
	
	public TransportationSystem() {
		stations = new Station[stationNumber];
		loadData();
	}
	
	public void loadData() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("./data/data.csv")));
			in.readLine();
			for (int i=0; i<stationNumber; i++) {
				String[] line = in.readLine().split(",");
				stations[i] = new Station(line[0], Float.parseFloat(line[2]), Float.parseFloat(line[3]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Station getStation(int index) {
		return stations[index];
	}
}
