package bb;

import java.util.LinkedList;
import java.util.List;

public class Node {
	
	//use for storing children nodes
	public List<Node> children = new LinkedList<Node>();
	
	//use for back-tracking
	public Node parent;
	
	//the current state
	public int[][] mat = new int[3][3];

	//the (x, y) position of 0 in the matrix 
	public int zero_x;
	public int zero_y;
	
	//initialize Node
	public Node(int[][] m) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = m[i][j];
				if(mat[i][j] == 0) {
					zero_x = i;
					zero_y = j;
				}
			}
		}
	}
	
	//find the states of all possible next moves
	//right if valid
	//left if valid	
	//up if valid
	//down if valid
	public void expand() {
		moveRight();
		moveLeft();
		moveUp();
		moveDown();
	}
	
	//move right if valid
	public void moveRight() {
		int move_x = zero_x;
		int move_y = zero_y + 1;
		
		if(move_y < 0 || move_y > 2) {
			
		}else {
			int[][] c = new int[3][3];
			copyMat(c, mat);
			
			int tmp = c[zero_x][zero_y];
			c[zero_x][zero_y] = c[move_x][move_y];
			c[move_x][move_y] = tmp;
			
			Node child = new Node(c);
			children.add(child);
			child.parent = this;
		}
	}
	
	//move left if valid
	public void moveLeft() {
		int move_x = zero_x;
		int move_y = zero_y - 1;
		
		if(move_y < 0 || move_y > 2) {
			
		}else {
			int[][] c = new int[3][3];
			copyMat(c, mat);
			
			int tmp = c[zero_x][zero_y];
			c[zero_x][zero_y] = c[move_x][move_y];
			c[move_x][move_y] = tmp;
			
			Node child = new Node(c);
			children.add(child);
			child.parent = this;
		}
	}
	
	//move up if valid
	public void moveUp() {
		int move_x = zero_x - 1;
		int move_y = zero_y;
		
		if(move_x < 0 || move_x > 2) {
			
		}else {
			int[][] c = new int[3][3];
			copyMat(c, mat);
			
			int tmp = c[zero_x][zero_y];
			c[zero_x][zero_y] = c[move_x][move_y];
			c[move_x][move_y] = tmp;
			
			Node child = new Node(c);
			children.add(child);
			child.parent = this;
		}
	}
	
	//move down if valid
	public void moveDown() {
		int move_x = zero_x + 1;
		int move_y = zero_y;
		
		if(move_x < 0 || move_x > 2) {
			
		}else {
			int[][] c = new int[3][3];
			copyMat(c, mat);
			
			int tmp = c[zero_x][zero_y];
			c[zero_x][zero_y] = c[move_x][move_y];
			c[move_x][move_y] = tmp;
			
			Node child = new Node(c);
			children.add(child);
			child.parent = this;
		}
	}
	
	//copy the values from matrix b to a
	public void copyMat(int[][] a, int b[][]) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				a[i][j] = b[i][j];
			}
		}
	}
	
	//check whether it reaches goal state or not
	public boolean goalTest() {
		int number = 1;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] != number) {
					return false;
				}
				number++;
				number = number % 9;
			}
		}
		return true;
	}
	
	//check whether two nodes share the same state
	public boolean isEqual(int[][] m) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] != m[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	//print it
	public void printMat() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
	
}
