package level2;

import java.util.Arrays;

class Solution1829 {
    static int numberOfArea;
    static int maxSizeOfOneArea;
    static int cnt;
    static boolean[][] visited;
    static int[][] directions = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    cnt = 0;
                    dfs(picture, picture[i][j], i, j);
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static void dfs(int[][] picture, int color, int x, int y) {
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx < 0 || picture.length <= nx ||
                    ny < 0 || picture[0].length <= ny) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (picture[nx][ny] == color) {
                visited[nx][ny] = true;
                cnt++;
                maxSizeOfOneArea = Math.max(
                        maxSizeOfOneArea, cnt
                );
                dfs(picture, color, nx, ny);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        new Solution1829().solution(6, 4, new int[][]{
                                {1, 1, 1, 0},
                                {1, 1, 1, 0},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1},
                                {0, 0, 0, 1},
                        })));

    }
}