package dataStructures;

import java.util.PriorityQueue;
import java.util.Stack;

import controller.MenuController;

public class MatrixGraph implements IGraph {
	private Stack<Integer> stack;
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private int VERTEX_LIMIT = 100;
    private int adj[][];
    private boolean[] color;
    private PriorityQueue<Node> pq;
    private int distance[];
	private int prev[]; 
	private MenuController controller;
    
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
	
	public int[][] getAdj() {
		return adj;
	}

    public MatrixGraph(boolean isDirected, MenuController controller) {
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.adj = new int[VERTEX_LIMIT][VERTEX_LIMIT];
        this.controller = controller;
    }  
    
    @Override
    public void addVertex() {
        vertexAmount++;
    }

    @Override
    public void removeVertex(int vertex) {
        for (int i=0; i<vertexAmount; i++) {
        	adj[i][vertex] = 0;
        }

        for (int i=0; i<vertexAmount-1-vertex; i++) {
            for (int j=0; j<vertexAmount; j++) {
                //System.out.println(i + "--> " + j);
            	adj[j][vertex+i] = adj[j][vertex+i+1];
            	adj[vertex+i][j] = adj[vertex+i+1][j];
            }
        }

        for (int i=0; i<vertexAmount-1; i++) {
        	adj[vertexAmount-1][i] = 0;
        	adj[i][vertexAmount-1] = 0;
        }

        vertexAmount--;
    }

    @Override
    public void addEdge(int from, int to, int weight) {
    	adj[from][to] = weight;
        if (isDirected==false) adj[to][from] = weight;
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
    	adj[from][to] = 0;
        if (isDirected==false) adj[to][from] = 0;
        edgeAmount--;
    }

    @Override
    public String printAdj(){
        String s= "";
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                s+= adj[i][j]+" ";
            }
            s+="\n";
        }
      return s;
    }
    
	@Override
    public void prim() {
		this.stack = new Stack<>();
        this.color = new boolean[VERTEX_LIMIT];
        this.pq = new PriorityQueue<>();
		primVisit(0);

    	while(!pq.isEmpty()){
        	int adjacent = pq.poll().getAdjacent();
	    	if(!color[adjacent]){
	    		primVisit(adjacent);
	    	}
    	}
    	
    	while (!stack.isEmpty()) {
    		controller.addRoute(stack.pop());
    	}
    }
	
	@Override
    public void primVisit(int u) {
    	stack.push(u);

    	controller.drawPoint(u);
    	//System.out.println("Se agrega " + ch[u] + "; -->RAIZ");
    	color[u] = true;
    	for(int i = 0; i < vertexAmount; i++){
    		if (adj[u][i] != 0 && !color[i]) {
    			controller.drawEdge(u, i);
    			pq.add(new Node(i, adj[u][i]));
    		}
    	}
    }
	
	@Override
	public void dijkstra(int s, int end) {
        this.pq = new PriorityQueue<>();
        this.color = new boolean[vertexAmount];
        this.distance = new int[vertexAmount];
        this.prev = new int[vertexAmount];
        for (int i=0; i<vertexAmount; i++) {
        	prev[i] = -1;
        	distance[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new Node(s, 0));
        distance[s] = 0;
		while(!pq.isEmpty()) {
			int current = pq.poll().getAdjacent();
			if (!color[current]) {
				color[current] = true;
				for (int i=0; i<vertexAmount; i++) {
					if (adj[current][i] != 0) {
						int weight = adj[current][i];
						if (!color[i]) {
							if (distance[current] + weight < distance[i]) {
								distance[i] = distance[current] + weight;
								prev[i] = current;
								//System.out.println("UPDATE "+ i +":" + distance[i] +"; PREV: " +prev[i]);
								pq.add(new Node(i, distance[i]));
							}
						}
					}

				}
			}
		}
		path(end);
	}

	public String path(int t) {
		String r = "";
		while(prev[t]!=-1){
			controller.drawPoint(t);
			controller.drawEdge(t, prev[t]);
			controller.addRoute(t);
			t=prev[t];
		}
		
		if(t!=-1){
			controller.drawPoint(t);
			controller.addRoute(t);
		}
		return r;
	}
}