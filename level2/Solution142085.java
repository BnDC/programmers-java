package level2;

import static java.util.Comparator.*;

import java.util.PriorityQueue;

public class Solution142085 {
	public int solution(int n, int k, int[] enemy) {
		int answer = 0, length = enemy.length;

		PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder());

		for (int i = 0; i < length; i++) {
			pq.offer(enemy[i]);
			n -= enemy[i];

			if (n < 0) {
				if (k <= 0) {
					break;
				}
				k--;
				n += pq.poll();
			}
			answer++;
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution142085 solution142085 = new Solution142085();
		System.out.println(solution142085.solution(7, 3, new int[] {4, 2, 4, 5, 3, 3, 1}) == 5);
		System.out.println(solution142085.solution(2, 4, new int[] {3, 3, 3, 3}) == 4);
	}
}
