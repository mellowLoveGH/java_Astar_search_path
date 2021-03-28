package bb;

import java.util.LinkedList;
import java.util.List;

public class Search {
	
	public long expanded;
	public long unexpanded;
	
	public Search() {
		
	}
	
	//use breadth-first algorithm to iterate the tree
	//stop when the goal node is found 
	public List<Node> breadthSearh(Node root){
		List<Node> path = new LinkedList<Node>();
		List<Node> toVisit = new LinkedList<Node>();
		List<Node> visited = new LinkedList<Node>();
		
		toVisit.add(root);
		boolean goal = false;
		
		while(toVisit.size() > 0 && !goal) {
			Node current = toVisit.get(0);
			visited.add(current);
			toVisit.remove(0);
			
			current.expand();
//			current.printMat();
			
			for (int i = 0; i < current.children.size(); i++) {
				Node child = current.children.get(i);
				if(child.goalTest()) {
					System.out.println("find it!");
					goal = true;
					backTrack(path, child);
				}
				
				if(!contains(toVisit, child) && !contains(visited, child)) {
					toVisit.add(child);
				}
			}
		}
		
		expanded = visited.size();
		unexpanded = toVisit.size();
		
		return path;
	}
	
	//by using the reference of parent 
	//to back track the path from the found goal node
	public void backTrack(List<Node> list, Node node) {
		System.out.println("back tracking...");
		Node current = node;
		list.add(current);
		while(current.parent != null) {
			current = current.parent;
			list.add(current);
		}
	}
	
	//check whether the list has one element that shares the same state with the node 
	public static boolean contains(List<Node> list, Node node) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isEqual(node.mat)) {
				return true;
			}
		}
		return false;
	}
	
}
