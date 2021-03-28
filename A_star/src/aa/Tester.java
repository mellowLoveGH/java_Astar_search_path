package aa;

import java.util.LinkedList;
import java.util.List;

public class Tester {
	
	public static void main(String[] args) {
		int[][] mat = {{0,5,4}, {7,2,1}, {6,3,8}};
		printMat(mat);
		Node.initFinalSate();
		
		List<Node> toVisit = new LinkedList<Node>();
		List<Node> visited = new LinkedList<Node>();
		
		Node root = new Node(mat);
//		root.expand();
		toVisit.add(root);
		Node goal = null;
		
		Node node = root;
		//start
		int N = 200;
		int loop = 0;
		while(!node.isGoal() && !toVisit.isEmpty()) {
			node = toVisit.get(0);
			node.expand();
//			System.out.println("visit: ");
//			printMat(node.state);
			
			visited.add(node);
			toVisit.remove(0);
			
			List<Node> cr = node.children;
//			System.out.println("children size: " + cr.size());
//			System.out.println("add new mat: ");
			for (int i = 0; i < cr.size(); i++) {
				Node tmp = cr.get(i);
				
				if(tmp.isGoal()) {
					goal = tmp;
					System.out.println("find it!");
					break;
				}
				if(!contains(toVisit, tmp) && !contains(visited, tmp)) {
					toVisit.add(tmp);
//					printMat(tmp.state);
				}
			}
			
//			printMat(goal.state);
//			System.out.println("to be visited: " + toVisit.size());
//			System.out.println("visited: " + visited.size());
			loop++;
		}
		System.out.println("program end");
		
		
	}
	
	public static boolean contains(List<Node> list, Node node) {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isEqual(node)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static void printMat(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + "\t");
				// System.out.print(mat[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
	}
	
}
