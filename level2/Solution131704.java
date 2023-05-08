package level2;

import java.util.Deque;
import java.util.LinkedList;

public class Solution131704 {
	public int solution(int[] order) {
		int answer = 0, now = 1;
		int length = order.length;

		Deque<Integer> stack = new LinkedList<>();

		for (int o : order) {
			if (!stack.isEmpty() && stack.peekLast() == o) {
				stack.pollLast();
				answer++;
				continue;
			}

			while (now <= length && now != o) {
				stack.offerLast(now++);
			}

			if (now > length) {
				break;
			} else {
				now++;
				answer++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution131704 solution131704 = new Solution131704();
		System.out.println(solution131704.solution(new int[] {4, 3, 1, 2, 5}) == 2);
		System.out.println(solution131704.solution(new int[] {5, 4, 3, 2, 1}) == 5);
	}
}
