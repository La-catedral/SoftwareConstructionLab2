package P2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;
import P2.Person;
//import P3.FriendshipGraph;

public class FriendshipGraph {
	private final Graph<Person> friendShipGraph = new ConcreteEdgesGraph<>();
	 // Abstraction function:
    //  AF(friendShipGraph) = the friendship graph composed by different people and there relationship
    // Representation invariant:
    //  Person in friendShipGraph are different 
    // Safety from rep exposure:
    // 	the field is private and final
	//	the parameter add to the mutator is immutable
   
	
	/**
	 * add a person on this graph
	 * @param the person you'd like to add
	 * @return true if succeeded,else false
	 */
	public boolean addVertex(Person newPerson)
	{
		return friendShipGraph.add(newPerson);	
	}
	
	/**
	 * add a edge composed by two person on the graph
	 * @param the two person that make up the tail and head of the edge
	 * @return 0 if succeed, 1 if already have the edge or illegal input
	 */
	public int addEdge(Person fromPerson,Person toPerson)
	{		
		if(fromPerson.equals(toPerson))
			return 1;
		return friendShipGraph.set(fromPerson,toPerson, 1);
	}
	/**
	 * get the distance of two person on the friendship graph
	 * @param the two person you want to get their distance
	 * @return the number of the edges from the first person to the second person
	 */
	public int getDistance(Person fromPerson, Person toPerson)
	{
		if(fromPerson.equals(toPerson))//same person
		return 0;
		if(!friendShipGraph.vertices().contains(fromPerson) || !friendShipGraph.vertices().contains(toPerson))
		return 0;
		//BFS search
		Map<Person,Integer> distanceMap = new HashMap<>();//record the distance from the first person
		Queue<Person> newQueue = new LinkedList<>();
		Set<Person> visited = new HashSet<>();
		newQueue.offer(fromPerson);
		distanceMap.put(fromPerson, 0);
		OUT:
		while(!newQueue.isEmpty())
		{
			Person thisPerson = newQueue.poll();
			visited.add(thisPerson);
			for(Person friendPerson:friendShipGraph.targets(thisPerson).keySet())
			{
				if(!friendShipGraph.targets(friendPerson).keySet().contains(thisPerson))//check if all the friends are legal friends
				{
					System.out.println(thisPerson.toString()+"和"+friendPerson.toString()+"间为违法的单向朋友关系");
					return -1;
				}
				if(!visited.contains(friendPerson))
				{
					distanceMap.put(friendPerson, distanceMap.get(thisPerson)+1);
					if(friendPerson.equals(toPerson))
					{
						break OUT;
					}
					newQueue.offer(friendPerson);
				}
			}
		}
		
		if(distanceMap.keySet().contains(toPerson))//if it has been found,return the distance
			return distanceMap.get(toPerson);
		else	//else they are not connected 
			return -1;
	}
	
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println(rachel+" 和 "+ross+":"+graph.getDistance(rachel, ross));
		// should print 1
		System.out.println(rachel+" 和 "+ben+":"+graph.getDistance(rachel, ben));
		// should print 2
		System.out.println(rachel+" 和 "+rachel+":"+graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println(rachel+" 和 "+kramer+":"+graph.getDistance(rachel, kramer));
		// should print -1
	}

}
