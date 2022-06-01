package dataStructures;
import java.util.*;

import model.Node;

public class ListGraph implements IGraph {
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private HashMap<Integer, HashMap<Integer, Integer>> ady;
    private int MAX=Integer.MAX_VALUE;
    private int distance[];//Estructura auxiliar para llevar las distancias a cada nodo
    private int prev[]; //Estructura auxiliar para almacenar las rutas
    private boolean marked[]; //Estructura auxiliar para marcar los nodos visitados
    private PriorityQueue<Node> pq;

    //private ArrayList[] ady = new ArrayList[VERTEX_LIMIT];

    public ListGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.ady = new HashMap<>();
        this.distance = new int[MAX];
        this.prev = new int[MAX];
        marked = new boolean[MAX];
        pq = new PriorityQueue<Node>();
        for (int i = 0; i < vertexAmount; i++) {
			distance[i]=MAX;
		}
    }
    
    public HashMap<Integer, HashMap<Integer, Integer>> getAdy(){
    	return ady;
    }
    @Override
    public boolean isDirected() {
		return this.isDirected;
	}

	public void setIsDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

    @Override
	public int getVertexAmount() {
		return this.vertexAmount;
	}

	public void setVertexAmount(int vertexAmount) {
		this.vertexAmount = vertexAmount;
	}

    @Override
	public int getEdgeAmount() {
		return this.edgeAmount;
	}

	public void setEdgeAmount(int edgeAmount) {
		this.edgeAmount = edgeAmount;
	}

    @Override
    public void addVertex() {
        ady.put(vertexAmount, new HashMap<>());
        vertexAmount++;
    }

    @Override
    public void removeVertex(int vertex) {
        ady.remove(vertex);
        for (HashMap<Integer, Integer> h : ady.values()) {
            if (h.containsKey(vertex)) h.remove(vertex);
        }
        vertexAmount--;
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        ady.get(from).put(to, weight);
        if (isDirected==false) ady.get(to).put(from, weight);
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
        ady.get(from).remove(to);
        if (isDirected==false) ady.get(to).remove(from);
        edgeAmount--;
    }

    @Override
    public String printAdy() {
    	String s="";
        for (Integer i : ady.keySet()) {
            s+=i + " --> ";
            for (Integer j : ady.get(i).keySet()) {
                s+=j + "-" + ady.get(i).get(j) + "; ";
            }
            s+="\n";
        }
        return s;
    }
    
    public void color(int index) {
    	marked[index]=true;
    	for (int i = 0; i<vertexAmount;i++) {
    		if (marked[i]==false) {
				pq.add(new Node(i,ady.get(index).get(i), index));
			}
		}
    }
    
	@Override
	public void Dijkstra(int s) {
		//se inserta a la cola el nodo Inicial.
	    distance[s] = 0;
	    color(s);
	    int actual, j, adjacent, weight;
	    Node x;

	    while( pq.size() > 0 ) {
	        actual = pq.peek().getAdjNode();
	        pq.poll();
	        if ( !marked[actual] ) {
	            marked[actual] = true;
	            for (Integer i : ady.get(actual).keySet()) {
					adjacent = i;
	                weight = ady.get(actual).get(i);
	                if ( !marked[adjacent] ) {
	                    if (distance[adjacent] > distance[actual] + weight) {
	                        distance[adjacent] = distance[actual] + weight;
	                        prev[adjacent] = actual;
	                        pq.add(new Node(adjacent, distance[adjacent],actual));
	                    }
	                }
				}
	        }
	    }
	}
	
	//Retorna en un String la ruta desde s hasta t
		//Recibe el nodo destino t
		public String path(int t) {
		    String r="";
		    while(prev[t]!=-1){
		        r="-"+t+r;
		        t=prev[t];
		    }
		    if(t!=-1){
		        r=t+r;
		    }
		    return r;
		}   
	   
}