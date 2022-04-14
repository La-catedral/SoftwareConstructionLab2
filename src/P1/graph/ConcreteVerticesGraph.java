/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
  
	private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   AF(vertices) = the vertices that compose this graph
    // Representation invariant:
    // 	Vertex must be non-null,and there are no same vertices
    // Safety from rep exposure:
    //  the field is private and final
    //  all the mutators' parameters are immutable
    //	used defensive copy in method sources() ,targets() ,vertices 
    
    // constructor
    // use default constructor 
    
    	private void checkRep()
    	{
    		for(int i =0;i<vertices.size();i++)
    		{
    			assert vertices.get(i) != null;
    			for(int j = i+1;j<vertices.size();j++)
    			{
    				assert !vertices.get(i).equals(vertices.get(j)) : "same vertices";
    			}
    		}
    	}
    @Override public boolean add(L vertex) {
        Vertex<L> realVertex = new Vertex<>(vertex);
    	if(!containThisVertex(realVertex))
        {
        	this.vertices.add(realVertex);
//        	checkRep();
        	return true;
        }
        else
        {
        	checkRep();
        	return false;
        }	
    }
 
    @Override public int set(L source, L target, int weight) {
        Vertex<L> tailVertex = this.findVertex(source, vertices);
        Vertex<L> headVertex = this.findVertex(target, vertices);
        if(tailVertex.getToVertices().keySet().contains(headVertex))
        {
        	int result = (int)(tailVertex.getToVertices().get(headVertex));
        	tailVertex.setToVertices(headVertex, weight);
        	headVertex.setFromVertices(tailVertex, weight);
//        	checkRep();
        	return result;
        }
        else
        {
        	if(weight != 0)
        	{
        		tailVertex.setToVertices(headVertex, weight);
            	headVertex.setFromVertices(tailVertex, weight);
        	}
//        	checkRep();
        	return 0;
        }
    }
    
    @Override public boolean remove(L vertex) {
        Vertex<L> newVertex = new Vertex<>(vertex);
        
    	if(containThisVertex(newVertex))
    	{
    		Vertex<L> realVertex =findVertex(vertex,vertices);
    		for(Vertex<L> toWhich : realVertex.getToVertices().keySet())
    		{
    			toWhich.getFromVertices().keySet().remove(realVertex);
    		}
    		
    		for(Vertex<L> fromWhich :(Set<Vertex<L>>)( realVertex.getFromVertices().keySet()))
    		{
    			fromWhich.getToVertices().keySet().remove(realVertex);
    		}
    		vertices.remove(realVertex);
//    		checkRep();
    		return true;	
    	}
    	else
    	{
//    		checkRep();
    		return false;
    	}
    }
    
   
	@Override public Set<L> vertices() {
        Set<L> newSet = new HashSet<>();
    	for(Vertex<L> thisVertex:vertices)
        {
        	newSet.add((L)(thisVertex.getVertices()));
        }
//    	checkRep();
    	return newSet;
    }
    
	@Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> newMap = new HashMap<>();
		Map<Vertex<L>,Integer> realMap =findVertex(target,vertices).getFromVertices();
        for(Vertex<L> thisVertex:realMap.keySet())
        {
        	newMap.put((L)(thisVertex.getVertices()), realMap.get(thisVertex));
        }
//        checkRep();
        return newMap;
    }
    
	@Override public Map<L, Integer> targets(L source) {
    	Map<L,Integer> newMap = new HashMap<>();
		Map<Vertex<L>,Integer> realMap =findVertex(source,vertices).getToVertices();
        for(Vertex<L> thisVertex:realMap.keySet())
        {
        	newMap.put((L)(thisVertex.getVertices()), realMap.get(thisVertex));
        }
//        checkRep();
        return newMap;
    }
    /**
     * find the vertex from the map 
     * @param a label which is the symbol of the vertex ,and a Map which exactly contains the vertex 
     * @return a vertex
     */  
	public  Vertex<L> findVertex(L label, List<Vertex<L>> vertices)
    {
    	Vertex<L> realVertex =new Vertex<>(label);
    	OUT:
    	for(Vertex<L> newVertex:vertices)
    	{
//    		或if (newBertex.equals(realVertex))
    		if(newVertex.toString().equals(label.toString()))
    		{
    			realVertex = newVertex;
    			break OUT;
    		}
    	}
//    	checkRep();
    	return realVertex;
    }

	/**
	 * find out if this graph contains this vertex
	 */
    public boolean containThisVertex(Vertex<L> vertex)
    {
    	if(vertex ==  null)
		{
			System.out.println("null!");
			return false;
		}
		else for(Vertex<L> thisVertex:vertices)
		{
			if(thisVertex.getVertices().equals(vertex.getVertices()))
				return true;
		}
		return false;
    }
	
	
     // toString()
    @Override
	public String toString() {
		String result =  vertices.toString() ;
		for(Vertex<L> thisVertex :vertices){
			Set<Vertex<L>> newSet = new HashSet<>();
			newSet = thisVertex.getToVertices().keySet();
			for(Vertex<L> friendVertex:newSet) {
				result = result+ " "+thisVertex+"->"+friendVertex;
			}
		}
		return result;
	}
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
	private final L vertices;
	private Map<Vertex<L>,Integer> toVertices =new HashMap<>();
	private Map<Vertex<L>,Integer> fromVertices =new HashMap<>();
    
    // Abstraction function:
    //   AF(vertices,toVertices,fromVertices) = the name/information of the vertex,the map that contain the vertex that have edge with it 
    // Representation invariant:
    //   vertices must be non-null,toVertices or fromVertices contain only positive weight 
    // Safety from rep exposure:
    //   vertices is private and final,also the type L is an immutable type
	// 	 all fields are private,the observer method return the unmodifiableMap,and all the parameter of 
	//	 the mutators are immutable ,which guarantee the safety 
    
    //  constructor
	public Vertex(L vertices) {
		this.vertices = vertices;
	}
    //  checkRep
	public void checkRep()
	{
		assert vertices != null;
		for (Vertex<L> thisVertex: toVertices.keySet())
		{
			int weight = toVertices.get(thisVertex);
			assert weight > 0:"Illegal weight";
		}	
		for (Vertex<L> thisVertex: fromVertices.keySet())
		{
			int weight = fromVertices.get(thisVertex);
			assert weight > 0:"Illegal weight";
		}
	}
    
    //  methods
	/**
	 * get the name/information from this Vertex
	 * @return this vertices' information
	 */
	public L getVertices()
	{
		return this.vertices;
	}
	/**
	 * get the vertices that have edge from it 
	 * @return the vertices and corresponding weight
	 */
	public Map<Vertex<L>,Integer> getToVertices()
	{
		return Collections.unmodifiableMap(this.toVertices);
	}
	
	/**
	 * get the vertices that have edge to it 
	 * @return the vertices and corresponding weight
	 */
	public Map<Vertex<L>,Integer> getFromVertices()
	{
		return Collections.unmodifiableMap(this.fromVertices);
	}
	public boolean containVertex(Vertex<L> vertex,Set<Vertex<L>> vertexSet)
	{
		if(vertex ==  null)
		{
			System.out.println("null!");
			return false;
		}
		else for(Vertex<L> thisVertex:vertexSet)
		{
			if(thisVertex.vertices.equals(vertex.vertices))
				return true;
		}
		return false;
		
	}
	/**
	 * set the head vertex and weight to an edge
	 * @param vertex,the vertex you want to set edge with it 
	 * @param weight, a nonnegative number
	 * @return  whether it is changed
	 */
	public boolean setToVertices(Vertex<L> vertex,Integer weight)
	{
		if(weight<0)
		{
			System.out.println("Illegal weight");
			return false;
		}
		if(!containVertex(vertex, toVertices.keySet()))
		{
			if(weight==0)
			{
				return false;
			}
			else 
			{
				this.toVertices.put(vertex,weight);
				return true;
			}
		}
		else
		{
			this.toVertices.keySet().remove(vertex);
			if(weight == 0)
			{
				return true;
			}
			else 
			{
				this.toVertices.put(vertex,weight);
				return true;
			}
		}	
	}
	
	/**
	 * set the tail vertex and weight to an edge
	 * @param vertex,the vertex you want to set edge with it 
	 * @param weight, a nonnegative number
	 * @return  whether it is changed
	 */
	public boolean setFromVertices(Vertex<L> vertex,Integer weight)
	{
		if(weight<0)
		{
			System.out.println("Illegal weight");
			return false;
		}
		if(!containVertex(vertex, fromVertices.keySet()))
		{
			if(weight==0)
			{
				return false;
			}
			else 
			{
				this.fromVertices.put(vertex,weight);
				return true;
			}
		}
		else
		{
			this.fromVertices.keySet().remove(vertex);
			if(weight == 0)
			{
				return true;
			}
			else 
			{
				this.fromVertices.put(vertex,weight);
				return true;
			}
		}	
	}

	

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this.toString().equals(obj.toString()))
//			return true;
//		else
//			return false;
//	}
	@Override
	public String toString() {
		return vertices.toString();
	}
}
