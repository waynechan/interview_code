package problem.merge_k_sorted_lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	
	public class Element {
		public int number;
		public int index;
	}
	
	Comparator<Element> comparator = new Comparator<Element>() {
		public int compare(Element x, Element y) {
			if (x.number > y.number) {
				return 1;
			} else if (x.number < y.number) {
				return -1;
			} else {
				return 0;
			}
		}
	};
	
	public List<Integer> merge(List<List<Integer>> lists) {

		PriorityQueue<Element> heap = new PriorityQueue<Element>(comparator);
		
		Map <Integer, Queue<Integer>> indexKeyQueueMap = new HashMap<Integer, Queue<Integer>>();
		
		int total = 0;
		for (int i = 0; i < lists.size(); i++) {
			List<Integer> list = lists.get(i);
			Queue<Integer> queue = new LinkedList<Integer>(list);			
			indexKeyQueueMap.put(i, queue);
			
			total = total + list.size();
		}
		
		for (int i = 0; i < lists.size(); i++) {
			Queue<Integer> queue = indexKeyQueueMap.get(i);
			
			if(queue.size() > 0) {
				int number = queue.remove();
				Element element = new Element();
				element.number = number;
				element.index = i;
				heap.add(element);
			}
		}
		
		List<Integer> result = new ArrayList<Integer>();

		for (int i=1 ; i<=total ; i++) {
			Element elementFromHeap = heap.remove();
			result.add(elementFromHeap.number);
			
			Queue<Integer> queue = indexKeyQueueMap.get(elementFromHeap.index);
			
			if(queue.size() > 0) {
				int number =queue.remove();
				Element elementToHeap = new Element();
				elementToHeap.number = number;
				elementToHeap.index = elementFromHeap.index;
				heap.add(elementToHeap);
			}
		}
		
		return result;
	}
}
