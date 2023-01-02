package level3;

public class Solution1836 {
    static int MOD = 20170805;

    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m + 1][n + 1][2];
        dp[0][0][1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    dp[i][j + 1][0] = (dp[i][j + 1][0] + dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i + 1][j][1] = (dp[i + 1][j][0] + dp[i][j][0] % MOD + dp[i][j][1]) % MOD;
                } else if (cityMap[i][j] == 2) {
                    dp[i][j + 1][0] = (dp[i][j + 1][0] + dp[i][j][0]) % MOD;
                    dp[i + 1][j][1] = (dp[i + 1][j][1] + dp[i][j][1]) % MOD;
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}) == 6);
        System.out.println(solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}) == 2);
    }
}
