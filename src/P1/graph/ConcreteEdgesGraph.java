/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // 	AF(vertices,edges) = the vertices and edges that made up this graph  
    // Representation invariant:
    //	the Object in vertices must be non-null
    //	the Edge in edges must be different,the tail and head of the edge is different and they are both in vertices
    // Safety from rep exposure:
    //  all fields are private and final
    //	all the mutators' parameters are immutable
    
    // constructor
    // use the default constructor 
    
    // checkRep
    private void checkRep()
    {
    	for(L vertex:vertices)
    	{
    		assert vertex != null;
    	}
    	for(int i = 0 ; i< edges.size();i++)
    	{
    		assert edges.get(i)!= null;
    		assert vertices.contains(edges.get(i).getSourceOfEdge());
    		assert vertices.contains(edges.get(i).getTargetOfEdge());
    		for(int j = i+1;j<edges.size();j++)
    		{
    			assert !(edges.get(i).getSourceOfEdge().equals(edges.get(j).getSourceOfEdge()) && edges.get(i).getTargetOfEdge().equals(edges.get(j).getTargetOfEdge())) : "same edge!";
    		}
    	}
    }
   
    
   
    @Override public boolean add(L vertex) {
    	if(!this.vertices.contains(vertex))
    	{
    		this.vertices.add(vertex);
//    		checkRep();
    		return true;
    		
    	}
    	else
    	{
//    		checkRep();
    		return false;
    	}
    }
    
	@Override public int set(L source, L target, int weight) {
		if(source.equals(target))
		{
			System.out.println("duplicate source and target!");
			return 0;
		}
		
		if(this.containEdge(source, target))
        {
        	Edge<L> newEdge = new Edge<>(source, target, 10);
        	Edge<L> theRealEdge =new Edge<>(source, target, 10);
        	OUT:
        	for(Edge<L> edgeInSet:edges) //use for loop to find the real edge 
        	{
        		if(edgeInSet.equals(newEdge))
        		{
        			theRealEdge =edgeInSet;
        			break OUT;
        		}
        	}
        	if(weight == 0)	//if graph contains this edge and weight is 0,remove it
        	{
        		edges.remove(theRealEdge);
        	}
        	checkRep();
        	return theRealEdge.resetWeight(weight);//otherwise reset the weight
        }
        else {
        	if(weight != 0 )
        	{
        		Edge<L> newEdge = new Edge<L>(source, target, weight);
        		this.edges.add(newEdge);
        	}
//        	checkRep();
        	return 0;
        }
        
    }
    
    @Override public boolean remove(L vertex) {
        if(this.vertices.contains(vertex))
        {
        	this.vertices.remove(vertex);
        	Iterator<Edge<L>> it = edges.iterator();
    		while(it.hasNext()){
    			Edge<L> item = it.next();
    			if (item.getSourceOfEdge().equals(vertex) || item.getTargetOfEdge().equals(vertex))
    			{
    				it.remove();//remove the current item
    			}
    		}
//        	checkRep();
        	return true;
        }
        else
        	{
//        	checkRep();
        	return false;
        	}
    }
    
    @Override public Set<L> vertices() {
        Set<L> theVertices =new HashSet<>();
    	for(L label:this.vertices)
        {
        	theVertices.add(label);
        }
//    	checkRep();
    	return theVertices;
    }
    
	@Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> theSources =new HashMap<>();
        for(Edge<L> newEdge:this.edges)
        {
        	if(newEdge.getTargetOfEdge().equals(target))
        	{
        		theSources.put((L)(newEdge.getSourceOfEdge()), newEdge.getWeight());
        	}
        }
//        checkRep();
        return theSources;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	Map<L,Integer> theTargets =new HashMap<>();
        for(Edge<L> newEdge:edges)
        {
        	if(newEdge.getSourceOfEdge().equals(source))
        	{
        		theTargets.put((L)(newEdge.getTargetOfEdge()), newEdge.getWeight());
        	}
        }
//        checkRep();
        return theTargets;
    }

    /**
     * to judge if the graph contain an edge composed by the two labels
     * @param source, the label of the edge tail
     * @param target, the label of the edge head
     * @return true if it is on the graph, else false
     */
    public boolean containEdge(L source,L target)
    {
    	Edge<L> newedge = new Edge<>(source, target, 10);
    	for(Edge<L> edgeInSet:edges)
    	{
//    		checkRep();
    		if(edgeInSet.equals(newedge))
    			return true;
    	}
    	return false;
    }
    // toString()

	@Override
	public String toString() {
		return  vertices +" and "+ edges ;
	}
    
    
}


/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	 private final L sourceOfEdge;
	 private final L targetOfEdge;
	 private int weightOfEdge;
    // Abstraction function:
    //	AF(sourceOfEdge,targetOfEdge,weightOfEdge) = the tail and head of the edge with its weight
    // Representation invariant:
    //  sourceOfEdge and targetOfEdge must be non null,weightOfedge must be positive
    // Safety from rep exposure:
    //	all the fields are private
	//	sourceOFEdge and targetOfEdge are immutable so all the rep are safe
    
    //  constructor
	 /**
	  * construct an edge 
	  * @param two labels and a positive number
	  * @return an edge
	  */
	 public Edge(L sourceOfEdge, L targetOfEdge, int weightOfEdge) {
			if(weightOfEdge <= 0)
			{
				System.out.println("WEIGHT SHOULD BE POSITIVE!");
				System.exit(0);
			}
			this.sourceOfEdge = sourceOfEdge;
			this.targetOfEdge = targetOfEdge;
			this.weightOfEdge = weightOfEdge;
			checkRep();
		}
    // checkRep
	 private void checkRep()
	 {
		 assert weightOfEdge > 0:"Illegal weight";
		 assert sourceOfEdge != null:"null source";
		 assert targetOfEdge != null:"null target";
		 
	 }
	
    
    // methods
	 
	 /**
	  * get the source label of the edge
	  * @param  no input
	  * @return the source label of this edge
	  */
	 public L getSourceOfEdge()
	 {
		 return this.sourceOfEdge;
	 }
	
	 /**
	  * get the target label of the edge
	  * @param  no input
	  * @return the target label of this edge
	  */
	 public L getTargetOfEdge()
	 {
		 return this.targetOfEdge;
	 }
	 
	 /**
	  * get the weight of the edge
	  * @param  no input
	  * @return the weight of this edge
	  */
	 public int getWeight()
	 {
		 return this.weightOfEdge;
	 }
	 /**
	  * reset the weight of this edge
	  * @param a nonnegative number you want the weight to be
	  * @return last weight of the edge
	  */
	 public int resetWeight(int newWeight)
	 {
		 if(newWeight < 0)
		 {
			 System.out.println("Illegal weight");
			 return -1;
		 }
		 int lastWeight =this.weightOfEdge;
		 this.weightOfEdge = newWeight;
		 return lastWeight;
	 }
	 
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sourceOfEdge == null) ? 0 : sourceOfEdge.hashCode());
		result = prime * result + ((targetOfEdge == null) ? 0 : targetOfEdge.hashCode());
		return result;
	}


	 @Override 
	 	public boolean equals(Object newedge)
	 	{
		 	return this.toString().equals(newedge.toString());
	 	}


	// TODO toString()
	 @Override
		public String toString() {
			return sourceOfEdge + " to " + targetOfEdge;
		}
    
}
