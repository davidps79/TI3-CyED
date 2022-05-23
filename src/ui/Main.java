package ui;

import dataStructures.IGraph;
import dataStructures.ListGraph;
import dataStructures.MatrixGraph;

public class Main {
	public static void main(String[] args) {
		Main main = new Main(2);
	}
	
	public Main(int op) {
		IGraph graph;
		if (op==1) {
			graph = new ListGraph(true);
		}else{
			graph = new MatrixGraph(true);
		}
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 1, 25);
        graph.addEdge(2, 3, 22);
        graph.addEdge(3, 4, 15);
        System.out.println(graph.printAdy());
        System.out.println("Deleting::");
        graph.removeVertex(1);
        System.out.println(graph.printAdy());
        System.out.println();
        System.out.println("vertex ammount:" + graph.getVertexAmount());
        System.out.println("edge ammount:" + graph.getEdgeAmount());
	}
}
