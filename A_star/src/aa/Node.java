package aa;

import java.util.LinkedList;
import java.util.List;

public class Node {

	public int[][] state;
	public Node parent;
	public List<Node> children;
	
	public static int[][] final_state = new int[3][3];
	public static void initFinalSate() {
		int number = 1;
		for (int i = 0; i < final_state.length; i++) {
			for (int j = 0; j < final_state[0].length; j++) {
				final_state[i][j] = number;
				number++;
			}
		}
		final_state[2][2] = 0;
	} 
	
	//root
	public Node(int[][] mat) {
		state = new int[3][3];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = mat[i][j];
			}
		}
		parent = null;
	}
	
	public Node(Node prt, int zero_x, int zero_y, int move_x, int move_y) {
		state = new int[3][3];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = prt.state[i][j];
			}
		}

		int tmp = state[zero_x][zero_y];
		state[zero_x][zero_y] = state[move_x][move_y];
		state[move_x][move_y] = tmp;
		
		this.parent = prt;
	}

	public void expand() {
		children = new LinkedList<Node>();

		int zero_x = 0, zero_y = 0;
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j] == 0) {
					zero_x = i;
					zero_y = j;
				}
			}
		}
		
		int move_x;
		int move_y;
		
		// when moving up
		move_x = zero_x - 1;
		move_y = zero_y;
		if (isValid(move_x, move_y)) {
			Node child = new Node(this, zero_x, zero_y, move_x, move_y);
			children.add(child);
		}
		
		// when moving down
		move_x = zero_x + 1;
		move_y = zero_y;
		if (isValid(move_x, move_y)) {
			Node child = new Node(this, zero_x, zero_y, move_x, move_y);
			children.add(child);
		}
		
		// when moving left
		move_x = zero_x;
		move_y = zero_y - 1;
		if (isValid(move_x, move_y)) {
			Node child = new Node(this, zero_x, zero_y, move_x, move_y);
			children.add(child);
		}
		
		// when moving right
		move_x = zero_x;
		move_y = zero_y + 1;
		if (isValid(move_x, move_y)) {
			Node child = new Node(this, zero_x, zero_y, move_x, move_y);
			children.add(child);
		}
	}

	public boolean isValid(int move_x, int move_y) {
		int left_bound = 0;
		int right_bound = 2;
		int up_bound = 0;
		int down_bound = 2;
		if (move_x < left_bound || move_x > right_bound || move_y < up_bound || move_y > down_bound) {
			return false;
		}
		return true;
	}
	
	public boolean isGoal() {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if(state[i][j] != final_state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isEqual(Node node) {
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if(state[i][j] != node.state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
}
