package model;

public class Node implements Comparable<Node>{
	private int node;
	private int adjNode;
	private int weigth;
	
	public Node(int adjNode, int weigth, int index) {
		this.adjNode=adjNode;
		this.weigth=weigth;
		this.node=index;
	}
	
	public void setAdjNode(int adjNode) {
		this.adjNode=adjNode;
	}
	
	public int getAdjNode() {
		return adjNode;
	}
	
	public void setWeigth(int weigth) {
		this.weigth=weigth;
	}
	
	public int getWeigth() {
		return weigth;
	}
	
	public int getNode() {
		return node;
	}

	@Override
	public int compareTo(Node other) {
		int result = this.getWeigth()-other.getWeigth();
		if (result==0) {
			Integer t= this.getAdjNode();
			Integer o= other.getAdjNode();
			return t.compareTo(o);
		}
		return result;
	}
}
