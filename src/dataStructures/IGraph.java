package dataStructures;

public interface IGraph {
    public void addVertex();
    public void removeVertex(int vertex);
    public void addEdge(int from, int to, int weight);
    public void removeEdge(int from, int to);
    public boolean isDirected();
    public int getVertexAmount();
    public int getEdgeAmount();
    public String printAdj();
    public void prim();
    public void primVisit(int currentVertex);
	public void dijkstra(int from, int to);
}
