package level3;

public class Solution161988 {
	public long solution(int[] sequence) {
		int length = sequence.length;
		if (length == 1)
			return Math.abs(sequence[0]);

		long answer = 0;
		long[][] dp = new long[length][2];

		for (int i = 0; i < length; i++) {
			if (i % 2 == 0) {
				dp[i][0] = sequence[i];
				dp[i][1] = sequence[i] * -1;
			} else {
				dp[i][0] = sequence[i] * -1;
				dp[i][1] = sequence[i];
			}
		}

		for (int i = 1; i < length; i++) {
			dp[i][0] = Math.max(dp[i][0], dp[i][0] + dp[i - 1][0]);
			dp[i][1] = Math.max(dp[i][1], dp[i][1] + dp[i - 1][1]);

			answer = Math.max(answer, dp[i][0]);
			answer = Math.max(answer, dp[i][1]);
		}

		return answer;
	}
}