/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
	// Poem() together with GraphPoet()
	// 		input a sentence ,output a reasonable extended sentence
	// 		invoke it twice with different input,observe the output 
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void testGraph()
    {
    	try {
			final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/myPoemData.txt"));
			assertTrue(nimoy.wordInGraph("You"));
			assertTrue(nimoy.wordInGraph("with"));
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testPoem()
    {
    	try {
			final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/myPoemData.txt"));
			final String input = "You get along with your professors or not has a effect on your growth.";
			assertEquals("You get along well with your professors or not has a huge effect on your self growth.",nimoy.poem(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/myPoemData2.txt"));
			final String input = "The important idea brings parents and children together.";
			assertEquals("The most important idea brings parents and children closer together.",nimoy.poem(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
