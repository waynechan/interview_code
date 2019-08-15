package problem.alien_language;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	
	private List<Character> postOrder = new ArrayList<>(); 
	
	private Set<Character> visitedNodes = new HashSet<>();
	
	private Set<Character> cycleNodes = new HashSet<>();
	
	private Boolean hasCycle = false;
	
	public class Graph {
		
		public Map<Character, List<Character>> adjacencyList;

		public Graph(Map<Character, List<Character>> adjacencyList) {
			this.adjacencyList = adjacencyList;
		}		
	}
	
	public List<Character> getOrders(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        
        Map<Character, List<Character>> adjacencyList = new HashMap<>();;
        
        for (int i=0 ; i<=words.length-2 ; i++) {
        	//check two adjacent words
        	String wordA = words[i];
        	String wordB = words[i+1];
        	int minLength = Math.min(wordA.length(), wordB.length());
        	for(int j=0 ; j<minLength ; j++) {
        		if (wordA.charAt(j) != wordB.charAt(j)) {
        			adjacencyList.putIfAbsent(wordA.charAt(j), new ArrayList<>());
        			if (!adjacencyList.get(wordA.charAt(j)).contains(wordB.charAt(j))) {
        				adjacencyList.get(wordA.charAt(j)).add(wordB.charAt(j));
        			}
        			break;
        		}
        	}
        }

        return getOrders(new Graph(adjacencyList));
    }
	
	public List<Character> getOrders(Graph graph) {
		for (Character node : graph.adjacencyList.keySet()) {
			dfs(graph, node);
		}
		
		if (hasCycle) {
			return new ArrayList<>();
		} else {
			Collections.reverse(postOrder);
		
			return postOrder;
		}
	}	
	
	private void dfs(Graph graph, Character node) {
		
		if (hasCycle) {
			return;
		}
		
		if (visitedNodes.contains(node)) {
			return;
		}
		
		if (cycleNodes.contains(node)) {
			hasCycle = true;
			return;
        }
        
		cycleNodes.add(node);
        
		List<Character> adjacencyList = graph.adjacencyList.get(node);
		if (adjacencyList != null) {
			for (Character adj : adjacencyList) {
				dfs(graph, adj);
			}
		}
		
		cycleNodes.remove(node);
		
		visitedNodes.add(node);
		
		postOrder.add(node);
	}
}
