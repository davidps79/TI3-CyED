package dataStructures;

import java.util.PriorityQueue;

import model.Node;

public class MatrixGraph implements IGraph {
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private int VERTEX_LIMIT = 100;
    private int ady[][];
    private int MAX=Integer.MAX_VALUE;
    private long distance[];//Estructura auxiliar para llevar las distancias a cada nodo
    private int prev[]; //Estructura auxiliar para almacenar las rutas
    private boolean marked[]; //Estructura auxiliar para marcar los nodos visitados
    private PriorityQueue<Node> pq;
    
    public MatrixGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.ady = new int[VERTEX_LIMIT][VERTEX_LIMIT];
        this.distance = new long[MAX];
        this.prev = new int[MAX];
        marked = new boolean[MAX];
        pq = new PriorityQueue<Node>();
    } 
    
    public boolean isDirected() {
		return this.isDirected;
	}

	public void setIsDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public int getVertexAmount() {
		return this.vertexAmount;
	}

	public void setVertexAmount(int vertexAmount) {
		this.vertexAmount = vertexAmount;
	}

	public int getEdgeAmount() {
		return this.edgeAmount;
	}

	public void setEdgeAmount(int edgeAmount) {
		this.edgeAmount = edgeAmount;
	}
	
	public int[][] getAdy() {
		return ady;
	}

    
    
    @Override
    public void addVertex() {
        vertexAmount++;
    }

    @Override
    public void removeVertex(int vertex) {
        for (int i=0; i<vertexAmount; i++) {
            ady[i][vertex] = 0;
        }

        for (int i=0; i<vertexAmount-1-vertex; i++) {
            for (int j=0; j<vertexAmount; j++) {
                //System.out.println(i + "--> " + j);
                ady[j][vertex+i] = ady[j][vertex+i+1];
                ady[vertex+i][j] = ady[vertex+i+1][j];
            }
        }

        for (int i=0; i<vertexAmount-1; i++) {
            ady[vertexAmount-1][i] = 0;
            ady[i][vertexAmount-1] = 0;
        }

        vertexAmount--;
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        ady[from][to] = weight;
        if (isDirected==false) ady[to][from] = weight;
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
        ady[from][to] = 0;
        if (isDirected==false) ady[to][from] = 0;
        edgeAmount--;
    }

    @Override
    public String printAdy(){
        String s= "";
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                s+= ady[i][j]+" ";
            }
            s+="\n";
        }
      return s;
    }
    /*
    public void color(int index) {
    	marked[index]=true;
    	for (int i = 0; i<vertexAmount;i++) {
    		if (marked[i]==false) {
				pq.add(new Node(i,ady.get(index).get(i), index));
			}
		}
    }*/
    
    
	//Recibe el nodo inicial s
	@Override
	public void Dijkstra(int s) {
		/*pq.add(new Node(s, 0, s));//se inserta a la cola el nodo Inicial.
	    distance[s] = 0;
	    int actual, j, adjacent;
	    long weight;
	    Node x;

	    while( pq.size() > 0 ) {
	        actual = pq.peek().getAdjNode();
	        pq.poll();
	        if ( !marked[actual] ) {
	            marked[actual] = true;
	            for (j = 0; j < ady[actual].size(); j++) {
	                adjacent = ady[actual].get(j).adjacent;
	                weight = ady[actual].get(j).cost;
	                if ( !marked[adjacent] ) {
	                    if (distance[adjacent] > distance[actual] + weight) {
	                        distance[adjacent] = distance[actual] + weight;
	                        prev[adjacent] = actual;
	                        pq.add(new Node(adjacent, distance[adjacent]));
	                    }
	                }
	            }
	        }
	    }*/
	}
	    
/*
	
	//Retorna en un String la ruta desde s hasta t
	//Recibe el nodo destino t
	static String path(int t) {
	    String r="";
	    while(prev[t]!=-1){
	        r="-"+t+r;
	        t=prev[t];
	    }
	    if(t!=-1){
	        r=t+r;
	    }
	    return r;
	}   */
}