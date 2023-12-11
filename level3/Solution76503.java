package level3;

import java.util.ArrayList;
import java.util.List;

// (lv3) 모두 0으로 만들기 - 성공
public class Solution76503 {
	int total;
	long absoluteSum;

	boolean[] visited;
	long[] globalA;

	public long solution(int[] a, int[][] edges) {
		total = a.length;

		visited = new boolean[total];
		globalA = new long[total];
		for (int i = 0; i < total; i++) {
			globalA[i] = a[i];
		}
		List<Integer>[] graph = new ArrayList[total];

		for (int i = 0; i < total; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < edges.length; i++) {
			int from = edges[i][0];
			int to = edges[i][1];
			graph[from].add(to);
			graph[to].add(from);
		}
		visited[0] = true;
		dfs(graph, 0);
		return globalA[0] == 0 ? absoluteSum : -1;
	}

	public long dfs(List<Integer>[] graph, int now) {
		for (int i = 0; i < graph[now].size(); i++) {
			int next = graph[now].get(i);
			if (visited[next]) continue;
			visited[next] = true;

			long before = dfs(graph, next);
			globalA[now] += before;
			absoluteSum += Math.abs(before);
		}
		return globalA[now];
	}
}
