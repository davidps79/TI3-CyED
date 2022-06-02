package dataStructures;

public class Node implements Comparable<Node> {
	private int adjacent;
	private int weight;
 
	public Node(int adjacent, int weight) {
	    this.adjacent = adjacent;
	    this.weight = weight;
    }
 
	@Override
	public int compareTo(Node other) {
	    if (this.weight > other.weight) return 1;
	    else if (this.weight == other.weight) {
	    	return ((Integer) this.getAdjacent()).compareTo((Integer) other.getAdjacent());
	    }
	    else return -1;
	}
	
	public int getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(int adjacent) {
		this.adjacent = adjacent;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return adjacent + " -> " + weight;
	}
}