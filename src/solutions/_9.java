/**
 * Golf Event 要砍树，每次只能砍最矮的那棵。0不可以走，1可以走，>1的代表树的高度。
 * 要求从原点开始，计算砍完所有树所需最短的步数与树高的总和。
 *
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class GolfCoordinate {
	int x, y;
	int val;

	GolfCoordinate(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}

class _9 {
	public static void main(String[] args) {
		_9 solution = new _9();

		int[][] fields = {
				{1, 1, 0, 2},
				{3, 1, 1, 1}
		};

		System.out.println(solution.flatFields(fields));
	}

	public int flatFields(int[][] fields) {
		if (fields == null || fields.length == 0 || fields[0].length == 0) {
			return 0;
		}

		PriorityQueue<GolfCoordinate> minHeap = new PriorityQueue<>(new Comparator<GolfCoordinate>() {
			@Override
			public int compare(GolfCoordinate c1, GolfCoordinate c2) {
				return c1.val - c2.val;
			}
		});
		int n = fields.length;
		int m = fields[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (fields[i][j] > 1) {
					minHeap.offer(new GolfCoordinate(i, j, fields[i][j]));
				}
			}
		}

		GolfCoordinate source = new GolfCoordinate(0, 0, fields[0][0]);
		int total = 0;

		while(!minHeap.isEmpty()) {
			GolfCoordinate destination = minHeap.poll();
			total += bfs(fields, source, destination);
			total += destination.val;
			//砍树
			fields[destination.x][destination.y] = 1;
			source = destination;
		}

		return total;
	}

	private int bfs(int[][] fields, GolfCoordinate source, GolfCoordinate destination) {
		Queue<GolfCoordinate> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[fields.length][fields[0].length];

		queue.add(source);
		isVisited[source.x][source.y] = true;

		int minSteps = 0;

		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};

		while(!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				GolfCoordinate cur = queue.poll();

				if (cur.x == destination.x && cur.y == destination.y) {
					return minSteps;
				}

				for (int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];

					if (isValid(fields, nx, ny) && !isVisited[nx][ny]) {
						queue.offer(new GolfCoordinate(nx, ny, fields[nx][ny]));
						isVisited[nx][ny] = true;
					}
				}
			}
			minSteps++;
		}
		return -1;
	}

	private boolean isValid(int[][] fields, int x, int y) {
		int n = fields.length;
		int m = fields[0].length;

		return x >= 0 && x < n && y >= 0 && y < m && fields[x][y] >= 1;
	}
}
