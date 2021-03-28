package aa;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AStarSearch1 {

	public static void main(String[] args) {
//		int[][] mat = new int[3][3];
//		initMat(mat);
		int[][] mat = {{0,5,4}, {7,2,1}, {6,3,8}};
//		int[][] mat = {{1,2,3}, {4,5,6}, {7,0,8}};
		printMat(mat);

		initFinalSate();
		// printMat(final_state);
		System.out.println(cost(mat));
		
		list.add(new MatState(mat));
		int N = 1000*5;
		for (int i = 0; i < N; i++) {
			if(printList()) {
				System.out.println("zero find");
				break;
			}
			running(mat);
		}
		
		System.out.println("MIN: " + MIN);
	}
	
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
		
		list.add(new MatState(mat));
	}
	

	public static int[][] final_state = new int[3][3];
	public static int STEP = 0;
	public static List<MatState> list = new LinkedList<MatState>();
	public static List<MatState> visited = new LinkedList<MatState>();
	public static int MIN = Integer.MAX_VALUE;
	
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

	// calculate the distance for every tile
	public static int distance(int v, int x, int y) {
		int dis = 0;
		for (int i = 0; i < final_state.length; i++) {
			for (int j = 0; j < final_state[0].length; j++) {
				if (v == final_state[i][j]) {
					dis = Math.abs(i - x) + Math.abs(j - y);
					return dis;
				}
			}
		}
		return dis;
	}

	// cost for all the tiles
	public static int cost(int[][] mat) {
		int sum = 0;
//		for (int i = 0; i < mat.length; i++) {
//			for (int j = 0; j < mat[0].length; j++) {
//				sum = sum + distance(mat[i][j], i, j);
//			}
//		}
		
		for (int i = 0; i < final_state.length; i++) {
			for (int j = 0; j < final_state[0].length; j++) {
				if(mat[i][j] != 0 && final_state[i][j] != 0) {
					if(mat[i][j] == final_state[i][j]) {
						sum++;
					}
				}
			}
		}
		
		
		return 8 - sum;
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

	public static boolean isValid(int move_x, int move_y) {
		int left_bound = 0;
		int right_bound = 2;
		int up_bound = 0;
		int down_bound = 2;
		if (move_x < left_bound || move_x > right_bound || move_y < up_bound || move_y > down_bound) {
			return false;
		}

		return true;
	}

	public static void possibleRoute(int[][] mat) {
		// int current_dis = cost(mat);
		// the position of 0
		int zero_x = 0, zero_y = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 0) {
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
			MatState ms = new MatState(mat, zero_x, zero_y, move_x, move_y);
			ms.step = STEP;
			addIntoList(ms);
		}

		// when moving down
		move_x = zero_x + 1;
		move_y = zero_y;
		if (isValid(move_x, move_y)) {
			MatState ms = new MatState(mat, zero_x, zero_y, move_x, move_y);
			ms.step = STEP;
			addIntoList(ms);
		}

		// when moving left
		move_x = zero_x;
		move_y = zero_y - 1;
		if (isValid(move_x, move_y)) {
			MatState ms = new MatState(mat, zero_x, zero_y, move_x, move_y);
			ms.step = STEP;
			addIntoList(ms);
		}

		// when moving right
		move_x = zero_x;
		move_y = zero_y + 1;
		if (isValid(move_x, move_y)) {
			MatState ms = new MatState(mat, zero_x, zero_y, move_x, move_y);
			ms.step = STEP;
			addIntoList(ms);
		}
	}

	private static void addIntoList(MatState ms) {
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isEqual(ms)) {
				flag = true;
			}
		}

		for (int i = 0; i < visited.size(); i++) {
			if (visited.get(i).isEqual(ms)) {
				flag = true;
			}
		}

		if (flag) {

		} else {
			list.add(ms);
		}
	}

	private static void removeFromList(MatState ms) {
		MatState v = new MatState(ms.state);
		visited.add(v);
		list.remove(ms);
	}

	public static void running(int[][] mat) {
		STEP++;

		//
		Collections.sort(list, new Comparator<MatState>() {
			public int compare(MatState arg0, MatState arg1) {
				int hits0 = arg0.getCost();
				int hits1 = arg1.getCost();
				if (hits1 > hits0) {
					return 1;
				} else if (hits1 == hits0) {
					return 0;
				} else {
					return -1;
				}
			}
		});

		//
//		printList();
		MatState last = list.get(list.size() - 1);
		possibleRoute(last.state);
//		printMat(last.state);
//		System.out.println("step: " + last.step + ", distance: " + last.distance + "\t" + last.getCost());
//		System.out.println();
		
		//
		removeFromList(last);
	}
	
	public static boolean printList() {
		boolean flag = false;
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + "step: " + list.get(i).step + ", distance: " + list.get(i).distance + "\t" + list.get(i).getCost();
			str = str + "\n";
			if(MIN > list.get(i).distance) {
				MIN = list.get(i).distance;
			}
			if(MIN == 0) {
				flag = true;
			}
		}
//		System.out.println(str.trim());
//		System.out.println("))))))))))))))))))))");
		
		return flag;
	}
	
	
}
