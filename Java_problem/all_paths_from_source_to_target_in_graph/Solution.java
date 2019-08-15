package problem.all_paths_from_source_to_target_in_graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	
	public class Graph {
		
		public Map<String, List<String>> adjacencyList;

		public Graph(Map<String, List<String>> adjacencyList) {
			this.adjacencyList = adjacencyList;
		}		
	}
	
	private List<String> path  = new ArrayList<>();
    private Set<String> vertices  = new HashSet<>();

    public List<List<String>> getPaths(Graph graph, String source, String target) {
    	List<List<String>> result = new ArrayList<>();
        enumerate(result, graph, source, target);
        return result;
    }

    private void enumerate(List<List<String>> result, Graph graph, String vertex, String target) {

        path.add(vertex);
        vertices.add(vertex);

        if (vertex.equals(target)) {
        	result.add(new ArrayList<String>(path));
        } else {
            for (String adj : graph.adjacencyList.get(vertex)) {
                if (!vertices.contains(adj)) 
                	enumerate(result, graph, adj, target);
            }
        }

        // backtracking
        path.remove(path.size() - 1);
        vertices.remove(vertex);
    }
}
