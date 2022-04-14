/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import P1.graph.Graph;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    // add()
	//		use the add method twice,both input the same vertices
	//		expect that the method output True at the first time and output False the second time 
	// set()
	//		input two vertices and a positive number,expect 0 the first time
	//		still input that two vertices and 0,expect the number you input last time
	//		still input that two vertices and any number,expect 0
	// remove(L vertices)
	// 		partition the input as follows:
	//		vertices that has not been add,vertices has been add 
	//		observe the output
	// vertices()
	// 		no input
	// 		observe the output without add any vertices
	// 		observe the output with an existed Set after add two vertices
	// sources()
	// 		no input
	//		observe the output without set any edges
	// 		observe the output after add two edges
	// targets()
	//		no input
	//		observe the output without set any edges
	// 		observe the output after add two edges
	
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
//  other tests for instance methods of Graph
    @Test
    public void testAddVertices(){
    	Graph<String> graphForTest =emptyInstance();
    	String verticeForTest ="testString";
    	String anotherVerticeForTest ="testString";   	
    	assertTrue(graphForTest.add(verticeForTest));
    	assertFalse(graphForTest.add(anotherVerticeForTest));
    }
    @Test 
    public void testSetEdges() {
    	Graph<String> graphForTest =emptyInstance();
    	String firstVerticeForTest ="testStringOne";
    	String secondVerticeForTest ="testStringTwo";
    	graphForTest.add(firstVerticeForTest);
    	graphForTest.add(secondVerticeForTest);
    	assertEquals(0,graphForTest.set(firstVerticeForTest, secondVerticeForTest, 10));
    	assertEquals(10,graphForTest.set(firstVerticeForTest, secondVerticeForTest, 0));
    	assertEquals(0,graphForTest.set(firstVerticeForTest, secondVerticeForTest, 0));
    }
    
    @Test
    public void testRemoveVertices() {
    	Graph<String> graphForTest =emptyInstance();
    	String verticeForTest ="testString";
    	assertFalse(graphForTest.remove(verticeForTest));
    	graphForTest.add(verticeForTest);
    	assertTrue(graphForTest.remove(verticeForTest));
    }
    
    @Test
    public void testVertices() {
    	Graph<String> graphForTest =emptyInstance();
    	String firstVerticeForTest ="testStringOne";
    	String secondVerticeForTest ="testStringTwo";
    	
    	assertEquals(Collections.emptySet(), emptyInstance().vertices());
    	Set<String> setForTest =new HashSet<>();
    	setForTest.add(firstVerticeForTest);
    	setForTest.add(secondVerticeForTest);
    	graphForTest.add(firstVerticeForTest);
    	graphForTest.add(secondVerticeForTest);
    	assertEquals(setForTest,graphForTest.vertices());
    }
    
    @Test 
    public void testSources() {
    	Graph<String> graphForTest =emptyInstance();
    	String firstVerticeForTest ="testStringOne";
    	String secondVerticeForTest ="testStringTwo";
    	String thirdVerticeForTest = "testStringthree";
    	graphForTest.add(firstVerticeForTest);
    	graphForTest.add(secondVerticeForTest);
    	graphForTest.add(thirdVerticeForTest);
    	assertEquals(Collections.emptyMap(),graphForTest.sources(firstVerticeForTest));
    	graphForTest.set(firstVerticeForTest,secondVerticeForTest ,10);
    	graphForTest.set(thirdVerticeForTest,secondVerticeForTest ,20);
    	assertEquals(2,graphForTest.sources(secondVerticeForTest).size());
    	assertTrue(graphForTest.sources(secondVerticeForTest).containsKey(firstVerticeForTest));
    	assertEquals(10,(int)graphForTest.sources(secondVerticeForTest).get(firstVerticeForTest));
    	assertTrue(graphForTest.sources(secondVerticeForTest).containsKey(thirdVerticeForTest));
    	assertEquals(20,(int)graphForTest.sources(secondVerticeForTest).get(thirdVerticeForTest));
    }
    
    @Test
    public void testTargets() {
    	Graph<String> graphForTest =emptyInstance();
    	String firstVerticeForTest ="testStringOne";
    	String secondVerticeForTest ="testStringTwo";
    	String thirdVerticeForTest = "testStringthree";
    	graphForTest.add(firstVerticeForTest);
    	graphForTest.add(secondVerticeForTest);
    	graphForTest.add(thirdVerticeForTest);
    	assertEquals(Collections.emptyMap(),graphForTest.targets(firstVerticeForTest));
    	graphForTest.set(firstVerticeForTest,secondVerticeForTest ,10);
    	graphForTest.set(firstVerticeForTest,thirdVerticeForTest ,20);
    	assertEquals(2,graphForTest.targets(firstVerticeForTest).size());
    	assertTrue(graphForTest.targets(firstVerticeForTest).containsKey(secondVerticeForTest));
    	assertEquals(10,(int)graphForTest.targets(firstVerticeForTest).get(secondVerticeForTest));
    	assertTrue(graphForTest.targets(firstVerticeForTest).containsKey(thirdVerticeForTest));
    	assertEquals(20,(int)graphForTest.targets(firstVerticeForTest).get(thirdVerticeForTest));

    	
    }
    
    
}
