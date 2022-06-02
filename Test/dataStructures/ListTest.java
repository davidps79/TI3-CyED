package dataStructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListTest {
	
	ListGraph test;
	
	public void setupStage1() {
		test= new ListGraph(true, null);
	}
	
	public void setupStage2() {
		test= new ListGraph(true, null);
		for (int i = 0; i < 5; i++) {
			test.addVertex();
		}
		test.addEdge(0, 1, 10);
		test.addEdge(2, 4, 5);
		test.addEdge(0, 3, 12);
		test.addEdge(3, 1, 1);
	}
	
	public void setupStage3() {
		test= new ListGraph(false, null);
		for (int i = 0; i < 5; i++) {
			test.addVertex();
		}
		test.addEdge(0, 1, 10);
		test.addEdge(2, 4, 5);
		test.addEdge(0, 3, 12);
		test.addEdge(3, 1, 1);
	}
	
	
	@Test 
	void addVertexTest() {
		setupStage1();
		int vertex=6;
		for (int i = 0; i < vertex; i++) {
			test.addVertex();
		}
		assertEquals(vertex,test.getVertexAmount());
		
	}
	
	@Test 
	void addEdgeDirectedGraphTest() {
		setupStage2();
		assertEquals(10,test.getAdj().get(0).get(1));
		assertEquals(null,test.getAdj().get(1).get(0));
		
		assertEquals(5,test.getAdj().get(2).get(4));
		assertEquals(null,test.getAdj().get(4).get(2));
		
		assertEquals(12,test.getAdj().get(0).get(3));
		assertEquals(null,test.getAdj().get(3).get(0));
		
		assertEquals(1,test.getAdj().get(3).get(1));
		assertEquals(null,test.getAdj().get(1).get(3));
		
	}
	
	@Test 
	void addEdgeNotDirectedGraphTest() {
		setupStage3();
		assertEquals(10,test.getAdj().get(0).get(1));
		assertEquals(10,test.getAdj().get(1).get(0));
		
		assertEquals(5,test.getAdj().get(2).get(4));
		assertEquals(5,test.getAdj().get(4).get(2));
		
		assertEquals(12,test.getAdj().get(0).get(3));
		assertEquals(12,test.getAdj().get(3).get(0));
		
		assertEquals(1,test.getAdj().get(3).get(1));
		assertEquals(1,test.getAdj().get(1).get(3));
		
	}
	
	@Test
	void removeEdgeDirectedGraphTest() {
		setupStage2();
		test.removeEdge(0, 3);
		
		assertEquals(null,test.getAdj().get(0).get(3));
		assertEquals(null,test.getAdj().get(3).get(0));
		
	}
	
	@Test
	void removeEdgeNotDirectedGraphTest() {
		setupStage3();
		test.removeEdge(1, 3);
		
		assertEquals(null,test.getAdj().get(1).get(3));
		assertEquals(null,test.getAdj().get(3).get(1));
		
	}
	
	@Test
	void removeVertexTest() {
		setupStage2();
		test.removeVertex(1);
		assertEquals(null,test.getAdj().get(1));
		assertEquals(null,test.getAdj().get(3).get(1));
		
	}


}