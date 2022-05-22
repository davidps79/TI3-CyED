package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {
	
	MatrixGraph test;
	
	public void setupStage1() {
		test= new MatrixGraph(true);
	}
	
	public void setupStage2() {
		test= new MatrixGraph(true);
		for (int i = 0; i < 5; i++) {
			test.addVertex();
		}
		test.addEdge(0, 1, 10);
		test.addEdge(2, 4, 5);
		test.addEdge(0, 3, 12);
		test.addEdge(3, 1, 1);
	}
	
	public void setupStage3() {
		test= new MatrixGraph(false);
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
		assertEquals(10,test.getAdy()[0][1]);
		assertEquals(0,test.getAdy()[1][0]);
		
		assertEquals(5,test.getAdy()[2][4]);
		assertEquals(0,test.getAdy()[4][2]);
		
		assertEquals(12,test.getAdy()[0][3]);
		assertEquals(0,test.getAdy()[3][0]);
		
		assertEquals(1,test.getAdy()[3][1]);
		assertEquals(0,test.getAdy()[1][3]);
		
	}
	
	@Test 
	void addEdgeNotDirectedGraphTest() {
		setupStage3();
		assertEquals(10,test.getAdy()[0][1]);
		assertEquals(10,test.getAdy()[1][0]);
		
		assertEquals(5,test.getAdy()[2][4]);
		assertEquals(5,test.getAdy()[4][2]);
		
		assertEquals(12,test.getAdy()[0][3]);
		assertEquals(12,test.getAdy()[3][0]);
		
		assertEquals(1,test.getAdy()[3][1]);
		assertEquals(1,test.getAdy()[1][3]);
		
	}
	
	@Test
	void removeEdgeDirectedGraphTest() {
		setupStage2();
		test.removeEdge(0, 3);
		
		assertEquals(0,test.getAdy()[0][3]);
		assertEquals(0,test.getAdy()[3][0]);
		
	}
	
	@Test
	void removeEdgeNotDirectedGraphTest() {
		setupStage3();
		test.removeEdge(1, 3);
		
		assertEquals(0,test.getAdy()[3][1]);
		assertEquals(0,test.getAdy()[1][3]);
		
	}


}
