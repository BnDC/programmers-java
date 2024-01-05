package level3;

import java.util.Arrays;

public class Solution138475 {
	public int[] solution(int e, int[] starts) {
		int[] answer = new int[starts.length];
		int[] factors = new int[e + 1];
		int[] maxNumbers = new int[e + 1];
		int[] max = new int[2];

		// 약수 구하기
		for (int i = 1; i <= e; i++) {
			for (int j = 1; j <= e / i; j++) {
				factors[i * j]++;
			}
		}

		// 최댓값 구하기
		for (int i = e; i >= 1; i--) {
			if (factors[i] >= max[1]) {
				max[1] = factors[i];
				max[0] = i;
			}
			maxNumbers[i] = max[0];
		}

		for (int i = 0; i < starts.length; i++) {
			answer[i] = maxNumbers[starts[i]];
		}
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.equals(new Solution138475().solution(8, new int[] {1, 3, 7}), new int[] {6, 6, 8}));
	}
}
