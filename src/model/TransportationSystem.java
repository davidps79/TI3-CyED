package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import controller.MenuController;
import dataStructures.IGraph;
import dataStructures.ListGraph;
import dataStructures.MatrixGraph;

public class TransportationSystem {
	private final int stationNumber = 59;
	private final int edgeNumber = 61;
	private HashMap<String, Integer> stationList;
	private Station[] stations;
	private MatrixGraph mGraph;
	private ListGraph lGraph;
	
	public TransportationSystem(MenuController controller) {
		this.mGraph = new MatrixGraph(false, controller);
		this.lGraph = new ListGraph(false, controller);
		
		for (int i=0; i<stationNumber; i++) {
			mGraph.addVertex();
			lGraph.addVertex();
		}

		stationList = new HashMap<>();
		stations = new Station[stationNumber];
		loadData();
		loadGraph();
	}
	
	public void loadData() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("./data/data.csv")));
			in.readLine();
			for (int i=0; i<stationNumber; i++) {
				String[] line = in.readLine().split(",");
				stations[i] = new Station(line[0], Float.parseFloat(line[2]), Float.parseFloat(line[3]));
				stationList.put(line[0], Integer.parseInt(line[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void loadGraph() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("./data/graph.csv")));
			in.readLine();
			for (int i=0; i<edgeNumber; i++) {
				String[] line = in.readLine().split(",");
				int from = Integer.parseInt(line[0]);
				int to = Integer.parseInt(line[1]);
				int w = Integer.parseInt(line[2]);
				mGraph.addEdge(from, to, w);
				lGraph.addEdge(from, to, w);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getStationList(){
		ArrayList<String> arr = new ArrayList<String>(stationList.keySet());
		Collections.sort(arr);
		return arr;
	}
	
	public Station getStation(int index) {
		return stations[index];
	}

	public void calculateRoute(String from, String to, String implementation) {
		int fromIndex = stationList.get(from);
		int toIndex = stationList.get(to);		
		
		IGraph graph;
		if (implementation.equals("Lista de ady.")) graph = lGraph;
		else graph = mGraph;
		
		graph.dijkstra(fromIndex, toIndex);
	}

	public void calculateAllRoutes(String implementation) {
		IGraph graph;
		if (implementation.equals("Lista de ady.")) graph = lGraph;
		else graph = mGraph;
		graph.prim();
	}
}
