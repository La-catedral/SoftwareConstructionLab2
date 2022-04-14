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
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    //  test other vertex label types in Problem 3.2
    // use the Integer type 
    // test if it can make an instance and
    // add a label
    public void testotherTypes()
    {
    	Graph<Integer> testGraph = new ConcreteEdgesGraph<Integer>();
    	Set<Integer> newSet = new HashSet<>();
    	Integer newInteger = 1;
    	testGraph.add(newInteger);
    	newSet .add(newInteger);
    	assertEquals(newSet,testGraph.vertices());
     }
    
//  test other vertex label types in Problem 3.2
    // use the Character type 
    // test if it can make an instance and
    // add a label
    public void testCharacterType()
    {
    	Graph<Character> testGraph = new ConcreteEdgesGraph<Character>();
    	Set<Character> newSet = new HashSet<>();
    	Character newChar ='a';
    	testGraph.add(newChar);
    	newSet .add(newChar);
    	assertEquals(newSet,testGraph.vertices());
     }
    
}
