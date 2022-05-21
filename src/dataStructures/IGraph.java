public interface IGraph {
    public void addVertex();
    public void removeVertex(int vertex);
    public void addEdge(int from, int to, int weight);
    public void removeEdge(int from, int to);
    public boolean isDirected();
    public int getVertexAmount();
    public int getEdgeAmount();
    public void printAdy();
}
