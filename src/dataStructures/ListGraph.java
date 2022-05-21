import java.util.*;

public class ListGraph implements IGraph {
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private HashMap<Integer, HashMap<Integer, Integer>> ady;

    //private ArrayList[] ady = new ArrayList[VERTEX_LIMIT];

    public ListGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.ady = new HashMap<>();
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
        if (!isDirected) ady.get(to).put(from, weight);
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
        ady.get(from).remove(to);
        if (!isDirected) ady.get(to).remove(from);
        edgeAmount--;
    }

    @Override
    public void printAdy() {
        for (Integer i : ady.keySet()) {
            System.out.print(i + " --> ");
            for (Integer j : ady.get(i).keySet()) {
                System.out.print(j + "-" + ady.get(i).get(j) + "; ");
            }
            System.out.println("");
        }
    }
}