package aa;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class AStarSearch {
	
	public static void main(String[] args) {
		
		int[][] mat = new int[3][3];
		initMat(mat);
//		int[][] mat = {{1,2,3}, {0,4,6}, {7,5,8}};
		printMat(mat);
		
		initFinalSate();
//		printMat(final_state);
		System.out.println(cost(mat));
		
		move(mat);
	}
	
	public static int[][] final_state = new int[3][3];
	public static Stack<MatState> stack = new Stack<MatState>();
	public static List<MatState> visited = new LinkedList<MatState>();
	
	public static void initMat(int[][] mat) {
		int number = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = number;
				number++;
			}
		}
		
		int N = 5;
		Random ran = new Random();
		for (int i = 0; i < N; i++) {
			int x1 = ran.nextInt(3);
			int y1 = ran.nextInt(3);
			int x2 = ran.nextInt(3);
			int y2 = ran.nextInt(3);
			
			int tmp = mat[x1][y1];
			mat[x1][y1] = mat[x2][y2];
			mat[x2][y2] = tmp;
		}
	}
	
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
	
	//find the best move to reduce the distance from the final state
	public static void move(int[][] mat) {
		int dis = cost(mat);
		
//		stack.push(new MatState(mat));
		addIntoList(new MatState(mat));

//		while(dis != 0) {
//			System.out.print("size : " + stack.size() + "\t");
//			possibleRoute(mat);
//			System.out.println("size: " + stack.size());
//			MatState step = stack.pop();
//			mat = step.state;
//			printMat(mat);
//			dis = cost(mat);
//			System.out.println(dis);
//		}
		
//		System.out.print("size : " + stack.size() + "\t");
		int N = 100;
		for (int i = 0; i < N; i++) {
			System.out.println("add mat: ");
			possibleRoute(mat);
			System.out.println("size: " + stack.size());
			System.out.println("choose this one: ");
			MatState step = stack.pop();
			addIntoList(step);
			mat = step.state;
			printMat(mat);
			dis = cost(mat);
			System.out.println(dis);
			System.out.println("size of list: " + visited.size());
		}
		
	}
	
	
	//add the possible routes into the stack
	public static boolean possibleRoute(int[][] mat) {
		int current_dis = cost(mat);
		
		//the position of 0
		int zero_x = 0, zero_y = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if(mat[i][j] == 0) {
					zero_x = i;
					zero_y = j;
				}
			}
		}
		
		//when moving left
		int move_left_x;
		move_left_x = zero_x - 1;
		int move_left_y;
		move_left_y = zero_y;
		int move_left_dis = evaluateMove(mat, zero_x, zero_y, move_left_x, move_left_y);
		 
		//when moving right
		int move_right_x;
		move_right_x = zero_x + 1;
		int move_right_y;
		move_right_y = zero_y;
		int move_right_dis = evaluateMove(mat, zero_x, zero_y, move_right_x, move_right_y);
		
		//when moving up
		int move_up_x;
		move_up_x = zero_x;
		int move_up_y;
		move_up_y = zero_y - 1;
		int move_up_dis = evaluateMove(mat, zero_x, zero_y, move_up_x, move_up_y);
		
		//when moving down
		int move_down_x;
		move_down_x = zero_x;
		int move_down_y;
		move_down_y = zero_y + 1;
		int move_down_dis = evaluateMove(mat, zero_x, zero_y, move_down_x, move_down_y);
		
		int min = current_dis;
		int move_x = zero_x, move_y = zero_y;
		
		List<MatState> list = new LinkedList<MatState>();
		
//		if(min >= move_left_dis) {
//			move_x = move_left_x;
//			move_y = move_left_y;
//			
//			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
//			list.add(state);
//		}
//		
//		if(min >= move_right_dis) {
//			move_x = move_right_x;
//			move_y = move_right_y;
//			
//			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
//			list.add(state);
//		}
//		
//		if(min >= move_up_dis) {
//			move_x = move_up_x;
//			move_y = move_up_y;
//			
//			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
//			list.add(state);
//		}
//		
//		if(min >= move_down_dis) {
//			move_x = move_down_x;
//			move_y = move_down_y;
//			
//			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
//			list.add(state);
//		}
		
		if(move_left_dis != -1) {
			move_x = move_left_x;
			move_y = move_left_y;
			
			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
			list.add(state);
		}
		
		if(move_right_dis != -1) {
			move_x = move_right_x;
			move_y = move_right_y;
			
			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
			list.add(state);
		}
		
		if(move_up_dis != -1) {
			move_x = move_up_x;
			move_y = move_up_y;
			
			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
			list.add(state);
		}
		
		if(move_down_dis != -1) {
			move_x = move_down_x;
			move_y = move_down_y;
			
			MatState state = new MatState(mat, zero_x, zero_y, move_x, move_y);
			list.add(state);
		}
		
		Collections.sort(list, new Comparator<MatState>() {  
            public int compare(MatState arg0, MatState arg1) {  
                int hits0 = arg0.distance;  
                int hits1 = arg1.distance;  
                if (hits1 > hits0) {  
                    return 1;  
                } else if (hits1 == hits0) {  
                    return 0;  
                } else {  
                    return -1;  
                }  
            }  
        });
		
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
		
		return true;
	}
	
	public static void addIntoList(MatState step) {
		MatState st = new MatState(step.state);
		visited.add(st);
	}
	
	public static void add(MatState state) {
		
		//
		for (int i = 0; i < visited.size(); i++) {
			if(visited.get(i).isEqual(state)) {
				System.out.println("in)))))))))))))");
				return;
			}
		}
		
		
		Stack<MatState> tmp_stack = new Stack<MatState>();
		boolean flag = false;
		
		while(!stack.isEmpty()) {
			MatState st = stack.pop();
			tmp_stack.push(st);
			if(st.isEqual(state)) {
				System.out.println("equal)))))))))))))");
				flag = true;
			}
		}
		
		while(!tmp_stack.isEmpty()) {
			MatState st = tmp_stack.pop();
			stack.push(st);
		}
		
		if(!flag) {
			stack.push(state);
		}
		
	}
	
	
	//evaluate for every move
	public static int evaluateMove(int[][] mat, int zero_x, int zero_y, int move_x, int move_y) {
//		System.out.println("))))))))))))))))))))))");
		int left_bound = 0;
		int right_bound = 2;
		int up_bound = 0;
		int down_bound = 2;
		
		if(move_x < left_bound || move_x > right_bound
				|| move_y < up_bound || move_y > down_bound) {
			return -1;
		}
		
		int[][] update = new int[3][3];
		for (int i = 0; i < update.length; i++) {
			for (int j = 0; j < update[0].length; j++) {
				update[i][j] = mat[i][j];
			}
		}
		
		int tmp = update[zero_x][zero_y];
		update[zero_x][zero_y] = update[move_x][move_y];
		update[move_x][move_y] = tmp;
		int dis = cost(update);
//		printMat(update);
//		System.out.println(dis);
//		printMat(mat);
		
		return dis;
	}
	
	//cost for all the tiles
	public static int cost(int[][] mat) {
		int sum = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				sum = sum + distance(mat[i][j], i, j);
			}
		}
		return sum;
	}
	
	//calculate the distance for every tile
	public static int distance(int v, int x, int y) {
		int dis = 0;
		for (int i = 0; i < final_state.length; i++) {
			for (int j = 0; j < final_state[0].length; j++) {
				if(v == final_state[i][j]) {
					dis = Math.abs(i-x) + Math.abs(j-y);
					return dis;
				}
			}
		}
		return dis;
	}
	
	public static void printMat(int mat[][]) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
//				System.out.print(mat[i][j] + "\t");
				System.out.print(mat[i][j]);
			}
//			System.out.println();
		}
		System.out.println("-----------------------------");
	}
}
