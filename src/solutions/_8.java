/**
 *  Find the min steps of maze
 *
 *
 */
import java.util.LinkedList;
import java.util.Queue;

class Coordinate {
	int x, y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class _8 {
	public static void main(String[] args) {
		int maze[][] =
            {
                    {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                    {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                    {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}
            };

		int[] s = {0, 0};
		int[] b = {3, 4};
		_8 solutions = new _8();

		int dist = solutions.minSteps(maze, s, b);

		System.out.println(dist);
	}

	public int minSteps(int[][] matrix, int[] source, int[] destination) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return -1;
		}

		int n = matrix.length;
		int m = matrix[0].length;

		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};

		boolean[][] isVisited = new boolean[n][m];

		Queue<Coordinate> queue = new LinkedList<>();
		queue.offer(new Coordinate(source[0], source[1]));
		isVisited[source[0]][source[1]] = true;

		int minSteps = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Coordinate current = queue.poll();

				if (current.x == destination[0] && current.y == destination[1]) {
					return minSteps;
				}

				for (int j = 0; j < 4; j++) {
					int nx = current.x + dx[j];
					int ny = current.y + dy[j];

					if (nx >= 0 && nx < n && ny >= 0 && ny < m && !isVisited[nx][ny] && matrix[nx][ny] == 1) {
						isVisited[nx][ny] = true;
						queue.offer(new Coordinate(nx, ny));
					}
				}
			}
			minSteps++;
		}

		return -1;
	}
}
