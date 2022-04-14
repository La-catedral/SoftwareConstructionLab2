/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // 图无边有顶点 图无顶点无边 图有边有顶点 
    // 通过调用toString函数观察能否清楚的表述图
    
    //  tests for ConcreteEdgesGraph.toString()
    public void testToString()
    {
    	List<Edge<String>> edges = new ArrayList<>();
    	Set<String> vertices = new HashSet<>();
    	String jack = new String("Jack");
    	String lucy = new String("Lucy");
    	ConcreteEdgesGraph<String > newGraph = new ConcreteEdgesGraph<String>();
    	assertEquals(vertices+" and "+edges,newGraph.toString());
    	newGraph.add(jack);
    	vertices.add(jack);
    	assertEquals(vertices+" and "+edges,newGraph.toString());
    	newGraph.add(lucy);
    	vertices.add(lucy);
    	newGraph.set(jack ,lucy, 1);
    	Edge<String> newEdge = new Edge<>(jack,lucy,1);
    	edges.add(newEdge);
    	assertEquals(vertices+" and "+edges,newGraph.toString());
    }
    
    @Test
    public void testContainEdge()
    {
    	String testLabelOne = "tail";
    	String testLabelTwo = "head";
    	String testLabelThree = "wrongOne";
    	ConcreteEdgesGraph<String> newGraph = new ConcreteEdgesGraph<>();
    	newGraph.add(testLabelOne);
    	newGraph.add(testLabelTwo);
    	newGraph.add(testLabelThree);

    	newGraph.set(testLabelOne,testLabelTwo,10);
    	assertTrue(newGraph.containEdge(testLabelOne,testLabelTwo));
    	assertFalse(newGraph.containEdge(testLabelOne,testLabelThree));

    }
    
    
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // Edge<L>()
    // partition the inputs as follows:
    //	weight: <0 , 0 , >0
    // observe the result of getSourceOfEdge and getTargetOfEdge and getWeight
    
    // getSourceOfEdge()
    // no inputs 
    // observe if the output equals to this edge's sourceOfEdge
   
    // getTargetOfEdge()
    // no inputs
    // observe if the output equals to this edge's targetOfEdge
    
    // getWeight()
    // no inputs 
    // expected the output equals to this number's weight
   
    // resetWeight(int number)
    // partition the inputs as follows:
    // number < 0 , > 0 , =0
    // expected to output original weight at the first time and output first number the second time 
    
    // equals()
    // input an edge whose sourceOfEdge and targetOfEdge are similar to this
    // expected a "true" output
    // input an edge whose sourceOfEdge and targetOfEdge are not similar with this
    // expected a "false" output
    
    // TODO tests for operations of Edge
    @Test
    public void testEdge()
    {
    	String firstString = "from";
    	String secondString = "to";
//    	Edge<String> newEdge1 = new Edge<>(firstString,secondString,-1);//these two will get tips and exit
//    	Edge<String> newEdge2 = new Edge<>(firstString,secondString,0);
    	Edge<String> newEdge = new Edge<>(firstString,secondString,10);
    	assertEquals(firstString,newEdge.getSourceOfEdge());
    	assertEquals(secondString,newEdge.getTargetOfEdge());
    	assertEquals(10,newEdge.getWeight());
    }
    
    @Test
    public void testGetSourceOfEdge()
    {
    	String firstString = "from";
    	String secondString = "to";
    	Edge<String> newEdge = new Edge<>(firstString,secondString,10);
    	assertEquals(firstString,newEdge.getSourceOfEdge());
    }
    
    @Test
    public void testGetTargetOfEdge()
    {
    	String firstString = "from";
    	String secondString = "to";
    	Edge<String> newEdge = new Edge<>(firstString,secondString,10);
    	assertEquals(secondString,newEdge.getTargetOfEdge());
    }
    
    @Test
    public void testWeightOfEdge()
    {
    	String firstString = "from";
    	String secondString = "to";
    	Edge<String> newEdge = new Edge<>(firstString,secondString,10);
    	assertEquals(10,newEdge.getWeight());
    }
    
    @Test
    public void testResetWeight()
    {
    	String firstString = "from";
    	String secondString = "to";
    	Edge<String> newEdge = new Edge<>(firstString,secondString,10);
    	assertEquals(10,newEdge.resetWeight(20));
    	assertEquals(-1,newEdge.resetWeight(-10));
    	assertEquals(20,newEdge.resetWeight(0));
    	assertEquals(0,newEdge.resetWeight(20));

    }
    
    @Test
    public void testEquals()
    {
    	String testStringOne = "firstString";
    	String testStringTwo = "secondString";
    	String testStringThree = "firstString";
    	String testStringFour = "secondString";
    	String testStringFive = "thirdString";
    	Edge<String> EdgeOne = new Edge<>(testStringOne,testStringTwo,10);
    	Edge<String> EdgeTwo = new Edge<>(testStringThree,testStringFour,10);
    	Edge<String> EdgeThree = new Edge<>(testStringOne,testStringFive,10);
    	assertTrue(EdgeOne.equals(EdgeTwo));
    	assertFalse(EdgeOne.equals(EdgeThree));
    }
    
}
