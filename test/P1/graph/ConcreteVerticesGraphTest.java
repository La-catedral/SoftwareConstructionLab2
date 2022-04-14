/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
import java.util.List;
//import java.util.Map;
//import java.util.Set;

import org.junit.Test;

import P1.graph.ConcreteVerticesGraph;
import P1.graph.Graph;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // partition the input as follows:
    // no vertex in graph,have vertex but no edge, have vertex and edge
    // containsVertex()
    // partition the input as :the vertex has been added ,has not been added to the graph
    
    // tests for ConcreteVerticesGraph.toString()
    public void testToString()
    {
    	ConcreteVerticesGraph<String> newGraph = new ConcreteVerticesGraph<String>();
    	List<String> newList = new ArrayList<>();
    	assertEquals(newList.toString(),newGraph.toString());
    	String jack = new String("Jack");
    	String lucy = new String("Lucy");
    	newList.add(jack);
    	newGraph.add(jack);
    	assertEquals(newList.toString(),newGraph.toString());
    	newList.add(lucy);
    	newGraph.add(lucy);
    	newGraph.set(jack, lucy, 1);
    	assertEquals(newList.toString()+" "+jack+"->"+"lucy",newGraph.toString());
    	
    }
    //covers: vertex has been added,vertex has not been added
    public void testContainsVertex()
    {
    	ConcreteVerticesGraph<String> newGraph = new ConcreteVerticesGraph<String>();
    	String jack = new String("Jack");
    	String lucy = new String("Lucy");
    	Vertex<String> JACK = new Vertex<>(jack);
    	Vertex<String> LUCY = new Vertex<>(lucy);

    	newGraph.add(jack);
    	assertTrue(newGraph.containThisVertex(JACK));
    	assertFalse(newGraph.containThisVertex(LUCY));
    	
    }
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // Vertex()
    // no input  
    // observe the vertices by method getVertices()
    
    // getVertices()
    // no input,output the label of this vertex
    // observe the output
    
    // getToVertices()
    // no input,output a Map whose key type is Vertex 
    // observe if the output map contain the vertices that the edge lead to,also observe the weight  
    // getFromVertices()
    // the same as getToVertices()
    
    // setToVertices(Vertex vertex,int weight)
    // partition the input as follows:
    // the weight: 0,>0,<0
    // observe the output
    // setFromVertices(Vertex vertex,int weight)
    // same strategy as last one
    
    // equals(Vertex vertex)
    // partition the input as follows:
    // vertex has same information with this object,vertex has different information
    // expected true if the label has same information as this
    
    // toString()
    // no input ,output the String format of its label 
    // observe the output 
    
    @Test
    public void testVertex()//如何写构造器的测试用例？
    {
    	String testString ="teststring";
    	Vertex<String> newVertex = new Vertex<>(testString);
    	assertEquals(testString,newVertex.getVertices());
    }
    
    @Test
    public void testGetvertices()
    {
    	String testString ="teststring";
    	Vertex<String> newVertex = new Vertex<>(testString);
    	assertEquals(testString,newVertex.getVertices());
    }
    
    @Test
    public void testGetToVertices()
    {
    	String testStringOne ="teststring";
    	String testStringTwo ="anotherteststring";
    	Vertex<String> firstVertex = new Vertex<>(testStringOne);
    	Vertex<String> secondVertex = new Vertex<>(testStringTwo);
    	firstVertex.setToVertices(secondVertex, 10);
    	assertTrue(firstVertex.getToVertices().keySet().contains(secondVertex));
    	assertEquals(10,(int)(firstVertex.getToVertices().get(secondVertex)));
    }
    
    @Test
    public void testGetFromVertices()
    {
    	String testStringOne ="teststring";
    	String testStringTwo ="anotherteststring";
    	Vertex<String> firstVertex = new Vertex<>(testStringOne);
    	Vertex<String> secondVertex = new Vertex<>(testStringTwo);
    	secondVertex.setFromVertices(firstVertex, 10);
    	assertTrue(secondVertex.getFromVertices().keySet().contains(firstVertex));
    	assertEquals(10,(int)(secondVertex.getFromVertices().get(firstVertex)));
    }
    
    @Test
    public void testSetToVertices()
    {
    	String testStringOne ="teststring";
    	String testStringTwo ="anotherteststring";
    	Vertex<String> firstVertex = new Vertex<>(testStringOne);
    	Vertex<String> secondVertex = new Vertex<>(testStringTwo);
    	assertFalse(firstVertex.setToVertices(secondVertex, 0));
    	assertFalse(firstVertex.setToVertices(secondVertex, -1));
    	assertTrue(firstVertex.setToVertices(secondVertex, 10));
    	assertEquals(10,(int)(firstVertex.getToVertices().get(secondVertex)));
    }
    
    @Test
    public void testSetFromVertices()
    {
    	String testStringOne ="teststring";
    	String testStringTwo ="anotherteststring";
    	Vertex<String> firstVertex = new Vertex<>(testStringOne);
    	Vertex<String> secondVertex = new Vertex<>(testStringTwo);
    	assertFalse(secondVertex.setFromVertices(firstVertex, 0));
    	assertFalse(secondVertex.setFromVertices(firstVertex, -1));
    	assertTrue(secondVertex.setFromVertices(firstVertex, 10));
    	assertEquals(10,(int)(secondVertex.getFromVertices().get(firstVertex)));
    }
    
}
