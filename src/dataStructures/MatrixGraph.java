public class MatrixGraph implements IGraph {
    private boolean isDirected;
    private int vertexAmount;
    private int edgeAmount;
    private int VERTEX_LIMIT = 100;
    private int ady[][];

    public static void main(String[] args) {
        ListGraph graph = new ListGraph(true);
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 1, 25);
        graph.addEdge(2, 3, 22);
        graph.addEdge(3, 4, 15);
        graph.printAdy();
        System.out.println("Eliminacosa:");
        graph.removeVertex(1);
        graph.printAdy();
        System.out.println();
        System.out.println("vertex ammount:" + graph.getVertexAmount());
        System.out.println("edge ammount:" + graph.getEdgeAmount());
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

    public MatrixGraph(boolean isDirected) {
        this.isDirected = isDirected;
        this.vertexAmount = 0;
        this.edgeAmount = 0;
        this.ady = new int[VERTEX_LIMIT][VERTEX_LIMIT];
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
        if (!isDirected) ady[to][from] = weight;
        edgeAmount++;
    }

    @Override
    public void removeEdge(int from, int to) {
        ady[from][to] = 0;
        if (!isDirected) ady[to][from] = 0;
        edgeAmount--;
    }

    @Override
    public void printAdy(){
        String s= "";
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                s+= ady[i][j]+" ";
            }
            s+="\n";
        }
        System.out.println(s);
    }
}