package interview;

class ColorGridCountChallenge {

	static char grid[][] = { 
			{ 'B', 'B', 'G', 'B', 'B', 'R', 'R', 'B', 'R', 'B' },
			{ 'B', 'R', 'R', 'R', 'G', 'R', 'R', 'R', 'G', 'B' }, 
			{ 'G', 'G', 'G', 'G', 'R', 'G', 'R', 'G', 'G', 'G' },
			{ 'G', 'G', 'G', 'B', 'G', 'B', 'B', 'B', 'R', 'G' }, 
			{ 'B', 'B', 'B', 'R', 'G', 'R', 'B', 'R', 'G', 'R' },
			{ 'R', 'B', 'B', 'R', 'R', 'B', 'B', 'R', 'G', 'R' },
			{ 'R', 'G', 'B', 'R', 'G', 'R', 'G', 'B', 'B', 'B' },
			{ 'B', 'G', 'R', 'R', 'R', 'R', 'B', 'B', 'R', 'R' }, 
			{ 'R', 'R', 'G', 'B', 'G', 'G', 'B', 'R', 'R', 'G' },
			{ 'R', 'B', 'B', 'B', 'B', 'B', 'R', 'B', 'B', 'G' }, 
			{ 'B', 'R', 'B', 'B', 'R', 'B', 'B', 'B', 'R', 'R' },
			{ 'B', 'R', 'B', 'B', 'G', 'B', 'B', 'B', 'G', 'R' } };

	static final int column = 12;
	static final int row = 10;
	static final char visited[][] = new char[column][row];
	static final char result[][] = new char[column][row];
	static int COUNT;

	static boolean isValid(int x, int y, int key, char input[][]) {
		if (x < column && y < row && x >= 0 && y >= 0) {
			if (visited[x][y] == 0 && input[x][y] == key)
				return true;
			else
				return false;
		} else
			return false;
	}

	static void traverse(int x, int y, int i, int j, char input[][]) {

		if (x != y)
			return;

		visited[i][j] = 1;
		COUNT++;

		int x_move[] = { 0, 0, 1, -1 };
		int y_move[] = { 1, -1, 0, 0 };

		for (int u = 0; u < 4; u++)
			if ((isValid(i + y_move[u], j + x_move[u], x, input)) == true)
				traverse(x, y, i + y_move[u], j + x_move[u], input);
	}

	static void resetVisited() {
		for (int i = 0; i < column; i++)
			for (int j = 0; j < row; j++)
				visited[i][j] = 0;
	}

	static void resetResult(int key, char input[][]) {
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (visited[i][j] == 1 && input[i][j] == key)
					result[i][j] = grid[i][j];
				else
					result[i][j] = 0;
			}
		}
	}

	static void printResult(int res) {

		System.out.println("largest block is :".concat(String.valueOf(res)));

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				if (result[i][j] != 0)
					System.out.print(String.valueOf(result[i][j]) + " ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}

	static void start(char input[][]) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				resetVisited();
				COUNT = 0;

				if (j + 1 < row)
					traverse(input[i][j], input[i][j + 1], i, j, input);

				if (COUNT >= max) {
					max = COUNT;
					resetResult(input[i][j], input);
				}
				resetVisited();
				COUNT = 0;

				if (i + 1 < column)
					traverse(input[i][j], input[i + 1][j], i, j, input);

				if (COUNT >= max) {
					max = COUNT;
					resetResult(input[i][j], input);
				}
			}
		}
		printResult(max);
	}

	public static void main(String args[]) {

		start(grid);
	}
}
