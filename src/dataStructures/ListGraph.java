package dataStructures;
import java.util.*;

import controller.MenuController;

public class ListGraph implements IGraph {
	private Stack<Integer> stack;
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private HashMap<Integer, HashMap<Integer, Integer>> adj;
    private boolean[] color;
    private PriorityQueue<Node> pq;
    private int distance[];
	private int prev[]; 
	private MenuController controller;
	
    public ListGraph(boolean isDirected, MenuController controller) {
        this.adj = new HashMap<>();
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.controller = controller;
    }
    
    public HashMap<Integer, HashMap<Integer, Integer>> getAdj(){
    	return adj;
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
    	adj.put(vertexAmount, new HashMap<>());
        vertexAmount++;
    }

    @Override
    public void removeVertex(int vertex) {
    	adj.remove(vertex);
        for (HashMap<Integer, Integer> h : adj.values()) {
            if (h.containsKey(vertex)) h.remove(vertex);
        }
        vertexAmount--;
    }

    @Override
    public void addEdge(int from, int to, int weight) {
    	adj.get(from).put(to, weight);
    	
        if (isDirected==false) adj.get(to).put(from, weight);
        
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
    	adj.get(from).remove(to);
        if (isDirected==false) adj.get(to).remove(from);
        edgeAmount--;
    }

    @Override
    public String printAdj() {
    	String s="";
        for (Integer i : adj.keySet()) {
            s+=i + " --> ";
            for (Integer j : adj.get(i).keySet()) {
                s+=j + "-" + adj.get(i).get(j) + "; ";
            }
            s+="\n";
        }
        return s;
    }

	@Override
    public void prim() {
		this.stack = new Stack<>();
        this.pq = new PriorityQueue<>();
        this.color = new boolean[vertexAmount];
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
    	color[u] = true;
    	
    	for (Integer i : adj.get(u).keySet()) {
    		if (!color[i]) {
    			controller.drawEdge(u, i);
    			pq.add(new Node(i, adj.get(u).get(i)));
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
				for (Integer i : adj.get(current).keySet()) {
					int weight = adj.get(current).get(i);
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