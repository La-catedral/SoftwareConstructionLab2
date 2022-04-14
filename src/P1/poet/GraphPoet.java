/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.File;

import java.io.IOException;
//import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Scanner;

//import P1.graph.ConcreteEdgesGraph;
import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   AF(graph) = a graph that composed by the strings that the file provided,which 
    //	can make the sentences that client input a poem
    // Representation invariant:
    //   the String in graph must be non-null 
    // Safety from rep exposure:
    //   the field graph is private and final 
    //	 there are no mutators in this class ,and all the label in the graph are immutable
    //	 thus it's safe from rep exposure
    
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	//先读取文件
    	//将文件成句子 或者行？
    	//将句子拆成单词 每个句子是单词组成的String数组
    	//生成一个空图 将每个单词（转换为小写）作为顶点插入图、 相邻单词建立有向边 重复的weight++
    	Scanner newScanner =new Scanner(corpus);
    	while(newScanner.hasNextLine())
    	{
    		String thisString = newScanner.nextLine();
    		thisString = thisString.replace('.', ' ');
    		String[] splitToWords = thisString.split(" ");
    		for(int i = 0;i < splitToWords.length;i++)
    		{
    			 String wordLabel = splitToWords[i].toLowerCase();
    			graph.add(wordLabel);
    			if(i!=0)
    			{
    				String lastLabel = splitToWords[i-1].toLowerCase();
    				int weight =1;
    				if(graph.targets(lastLabel).keySet().contains(wordLabel))
    				{
    					weight=graph.targets(lastLabel).get(wordLabel) + 1;
    				}
    				graph.set(lastLabel, wordLabel, weight);
    			}
    		}
    	}
    	newScanner.close();
    }
    
    //  checkRep
    public void checkRep()
    {
    	for (String thisString: graph.vertices())
    	{
    		assert thisString != null;
    	}
    }
    /**
     * find if a word is in the graph
     * @param the word you want to check
     * @return true if it does in the graph,else false
     */
    public boolean wordInGraph(String word)
    {
    	if(graph.vertices().contains(word.toLowerCase()))
    		return true;
    	else 
    		return false;
    }

    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	String dealedString = input.replace('.', ' ');
    	//拆成单词组
    	//从第一个开始 对于组内相邻两个单词 在图中找有无从前到后距离为2的，若有找weight最大的路
    	//把这个weight最大的路径中间的节点的单词插入arraylist（或者别的）
    	//循环直到读到最后一个单词 不用再找 直接加进去
    	//arraylist 转成string
    	String[] wordsOfString = dealedString.split(" ");
    	ArrayList<String> listForStorage = new ArrayList<>();
    	for(int i = 0;i < wordsOfString.length; i++)
    	{
    		listForStorage.add(wordsOfString[i]);
    		if(i < wordsOfString.length-1)
    		{	
	    		if(graph.vertices().contains(wordsOfString[i].toLowerCase()) && graph.vertices().contains(wordsOfString[i+1].toLowerCase()))
	    		{
	    			int maxWeight=0;
	        		String bestLabel="";
	    			for(String bridgeLabel:graph.targets(wordsOfString[i].toLowerCase()).keySet())
	    			{
	    				for(String lastLabel:graph.targets(bridgeLabel).keySet())
	    				{
	    					if(lastLabel.equals(wordsOfString[i+1]) )
	    					{
	    						int weightOfThisOne = graph.targets(wordsOfString[i].toLowerCase()).get(bridgeLabel) +graph.targets(bridgeLabel).get(lastLabel);
	    						if(weightOfThisOne > maxWeight)
	    						{
	    							bestLabel = bridgeLabel;
	    							maxWeight = weightOfThisOne;
	    						}
	    					}
	    				}
					}
	    			if(maxWeight != 0)			// if we have found the bridge 
	    			{							// the maxWeight must changed 	
	    				listForStorage.add(bestLabel.toString());
	    			}
	    		} 		
    		}
    	}
    	String wordInString,resultString = ""; //put the words in the String that 
    	for(int i = 0;i<listForStorage.size();i++)	// will be returned;
    	{
    		wordInString = listForStorage.get(i);
    		resultString = resultString + wordInString;
    		if(i < listForStorage.size()-1)
    		{
    			resultString = resultString + " ";
    		}
    		else {
    			resultString = resultString + ".";
    		}
    	}
    	return resultString;
 }
    
    
    //  toString()
	@Override
	public String toString() {
		return graph.toString() ;
	}
    
  
    
}
