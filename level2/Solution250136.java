package level2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

// lv2기 [PCCP 기출문제] 2번 / 석유 시추 - 성공
public class Solution250136 {
	int n, m;
	int groupCnt = 1;

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	int[][] group;

	Queue<Pos> q = new ArrayDeque<>();
	Map<Integer, Integer> map = new HashMap<>();

	public int solution(int[][] land) {
		int answer = 0;
		n = land.length;
		m = land[0].length;

		group = new int[n][m];
		Set<Integer> groupSet;

		for (int i = 0; i < m; i++) {
			int result = 0;
			groupSet = new HashSet<>();
			// System.out.print("i: " + i + " ");
			for (int j = 0; j < n; j++) {
				if (land[j][i] == 0)
					continue;
				if (group[j][i] == 0)
					bfs(land, new Pos(j, i));
				groupSet.add(group[j][i]);
			}
			// System.out.println(groupSet);
			for (Integer group : groupSet) {
				result += map.get(group);
			}
			answer = Math.max(answer, result);
		}

		// System.out.println(map);
		// for (int i = 0; i < n; i++) {
		//     for (int j = 0; j < m; j++) {
		//         System.out.print(group[i][j] + " ");
		//     }
		//     System.out.println();
		// }
		return answer;
	}

	/**
	 * @param land  : 문제에서 주어지는 land
	 * @param start : 시작점
	 *              그룹에 해당하는 좌표 표시
	 *              그룹의 크기를 산출해서, map에 저장
	 */
	public void bfs(int[][] land, Pos start) {
		int result = 1;
		group[start.x][start.y] = groupCnt;
		q.offer(start);

		while (!q.isEmpty()) {
			Pos now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (land[nx][ny] == 0 || group[nx][ny] != 0)
					continue;
				group[nx][ny] = groupCnt;
				q.offer(new Pos(nx, ny));
				result++;
			}
		}
		map.put(groupCnt++, result);
	}

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Pos pos = (Pos)o;
			return x == pos.x && y == pos.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	public static void main(String[] args) {
		Solution250136 solution = new Solution250136();

		System.out.println(solution.solution(
				new int[][] {
						{0, 0, 0, 1, 1, 1, 0, 0},
						{0, 0, 0, 0, 1, 1, 0, 0},
						{1, 1, 0, 0, 0, 1, 1, 0},
						{1, 1, 1, 0, 0, 0, 0, 0},
						{1, 1, 1, 0, 0, 0, 1, 1}
				}
		) == 9);
		System.out.println(solution.solution(
				new int[][] {
						{1, 0, 1, 0, 1, 1},
						{1, 0, 1, 0, 0, 0},
						{1, 0, 1, 0, 0, 1},
						{1, 0, 0, 1, 0, 0},
						{1, 0, 0, 1, 0, 1},
						{1, 0, 0, 0, 0, 0},
						{1, 1, 1, 1, 1, 1}
				}
		) == 16);

	}
}