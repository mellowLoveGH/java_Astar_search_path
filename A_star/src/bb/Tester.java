package bb;

import java.util.List;
import java.util.Random;

//for Odd board size
//in this assignment N = 3, it is odd number
//if a board has an odd number of inversions, 
//then it cannot lead to the goal board by a sequence of legal moves 
//because the goal board has an even number of inversions (zero)
public class Tester {
	public static void main(String[] args) {
		 int[][] mat = {{1,2,3}, {4,5,6}, {7,8,0}};
		 Random ran = new Random();		 
		 int N = 10*(ran.nextInt(5) + 3);
		 System.out.println(initBoard(mat, N));;
		 print(mat);
//		 int[][] mat = {{0,1,3}, {4,2,5}, {7,8,6}};
//		 int[][] mat = {{8,1,3}, {4,0,2}, {7,6,5}};
		 
		 
		 Node root = new Node(mat);
		 Search sch = new Search();
		
		 long start = System.currentTimeMillis();
		
		 List<Node> path = sch.breadthSearh(root);
		
		 if(path.size() > 0) {
			 for (int i = path.size() - 1; i >= 0; i--) {
				 path.get(i).printMat();
			 }
			 System.out.println("At least " + (path.size() - 1) + " moves to reach goal");
			 System.out.println("Number of nodes expanded " + sch.expanded);
			 System.out.println("Number of nodes unexpanded " + sch.unexpanded);
		 }else {
			 System.out.println("no path found");
		 }
		
		 long end = System.currentTimeMillis();
		 System.out.println((end - start)/1000 + " seconds");
	}
	
	
	//initialize the matrix
	//by changing the position of 0 with its neighbor for a certain times
	//mat, it is the matrix(board)
	//N, it means how many times of swaps
	//here also uses the Random to generate random number
	//random number is 0, swap the position of 0 with its upper one if valid
	//random number is 1, swap the position of 0 with its down one if valid
	//random number is 2, swap the position of 0 with its left one if valid
	//random number is 3, swap the position of 0 with its right one if valid
	//N, should not be too small or too big
	//if too small, the matrix(board) is not fully initialized
	//if too big, it will take too many moves to reach goal state, exponentially increasing the amount of computation
	public static int initBoard(int[][] mat, int N) {
		int moves = 0;
		Random ran = new Random();
		
		for (int i = 0; i < N; i++) {
			
			int[] pos = zeroPositions(mat);
			int zero_x = pos[0];
			int zero_y = pos[1];
			int move_x, move_y;
			
			int num = ran.nextInt(4);
			if(num == 0) {
				//up
				move_x = zero_x - 1;
				move_y = zero_y;
				if(isValid(move_x, move_y)) {
					swap(mat, zero_x, zero_y, move_x, move_y);
				}
			}else if(num == 1) {
				//down
				move_x = zero_x + 1;
				move_y = zero_y;
				if(isValid(move_x, move_y)) {
					swap(mat, zero_x, zero_y, move_x, move_y);
					moves++;
				}
			}else if(num == 2) {
				//left
				move_x = zero_x;
				move_y = zero_y - 1;
				if(isValid(move_x, move_y)) {
					swap(mat, zero_x, zero_y, move_x, move_y);
					moves++;
				}
			}else {
				//right
				move_x = zero_x;
				move_y = zero_y + 1;
				if(isValid(move_x, move_y)) {
					swap(mat, zero_x, zero_y, move_x, move_y);
					moves++;
				}
			}
			
//			print(mat);
		}
		
		return moves;
	}
	
	//print out the matrix(board)
	public static void print(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
	}
	
	//move, swap the position of 0 with its neighbor position
	public static void swap(int[][] mat, int zero_x, int zero_y, int move_x, int move_y) {
		int tmp = mat[zero_x][zero_y];
		mat[zero_x][zero_y] = mat[move_x][move_y];
		mat[move_x][move_y] = tmp;
	}
	
	//judge the move is valid or not
	public static boolean isValid(int x, int y) {
		if(x < 0 || x > 2) {
			return false;
		}
		if(y < 0 || y > 2) {
			return false;
		}
		return true;
	}
	
	//find the (x, y) positions of 0 in the matrix
	public static int[] zeroPositions(int[][] mat) {
		int[] pos = new int[2];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		return pos;
	}
}
