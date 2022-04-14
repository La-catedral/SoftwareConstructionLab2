package P2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FriendShipGraphTest {

	// test strategy
	// testAddEdge(Person a,Person b)
	//		partition the inputs as follows:
	//		the two person of the edge has not been added,has been added,two same person
	// testAddVertex(Person a)
	//		partition the inputs as follows:
	//		the person has not been add to the graph,and the person has been added
	// testGetDistance(Person fromWhom,Person toWhom)
	//		partition the inputs as follows:
	//		the two are same person,the two has been connected in the graph,the two has not been connected
	
	@Test
	public void testAddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		assertEquals(0,graph.addEdge(ross, rachel));
		assertEquals(1,graph.addEdge(ross, rachel));
		assertEquals(1,graph.addEdge(rachel, rachel));
		assertEquals(0,graph.addEdge(rachel, ben));
	}

	@Test
	public void testAddVertex() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("david");
		assertTrue(graph.addVertex(rachel));
		assertFalse(graph.addVertex(rachel));
	}

	

	@Test
	public void testGetDistance1() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rache");
		Person ross = new Person("Ros");
		Person ben = new Person("Be");
		Person kramer = new Person("Kram");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		assertEquals(1,graph.getDistance(rachel, ross));
		//should print 1
		assertEquals(2,graph.getDistance(rachel, ben));
		//should print 2
		assertEquals(0,graph.getDistance(rachel, rachel));
		//should print 0
		assertEquals(-1,graph.getDistance(rachel, kramer));
		//should print -1
	}
}
