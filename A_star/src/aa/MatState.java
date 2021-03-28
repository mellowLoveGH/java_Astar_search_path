package aa;

public class MatState {
	
	public int[][] state = null;
	public int distance = 0;
	public int step = 0;
	
	public MatState(int[][] mat) {
		state = new int[3][3];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = mat[i][j];
			}
		}
		distance = AStarSearch1.cost(state);
	}
	
	public MatState(int[][] mat, int zero_x, int zero_y, int move_x, int move_y) {
		state = new int[3][3];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				state[i][j] = mat[i][j];
			}
		}
		
		int tmp = state[zero_x][zero_y];
		state[zero_x][zero_y] = state[move_x][move_y];
		state[move_x][move_y] = tmp;
		
		distance = AStarSearch1.cost(state);
	}
	
	public boolean isEqual(MatState st) {
		if(st.distance != this.distance) {
			return false;
		}
		
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if(state[i][j] != st.state[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int getCost() {
//		return step + distance;
		return distance;
	}
	
}
