package level3;

// (lv3) 2차원 동전 뒤집기 - 성공
public class Solution131703 {
	int n, m;

	public int solution(int[][] beginning, int[][] target) {
		n = beginning.length;
		m = beginning[0].length;

		int[][] diffArr = new int[n][m];
		int[][] simulatedArr = new int[n][m];

		// diff 표시
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (beginning[i][j] != target[i][j]) {
					diffArr[i][j] = 1;
					simulatedArr[i][j] = 1;
				}
			}
		}

		// 처음 행 1 -> 0으로 바꾸었을 때, 바꾸는 횟수의 최솟값
		int result = 0;
		for (int i = 0; i < m; i++) {
			if (simulatedArr[0][i] == 1) {
				result++;
				toggleCol(simulatedArr, i);
			}
		}

		for (int i = 0; i < n; i++) {
			if (simulatedArr[i][0] == 1) {
				result++;
				toggleRow(simulatedArr, i);
			}
		}

		int answer = isPossible(simulatedArr) ? result : Integer.MAX_VALUE;

		// 초기화
		result = 0;
		for (int i = 0; i < n; i++) {
			System.arraycopy(diffArr[i], 0, simulatedArr[i], 0, m);
		}

		// 처음 행 0 -> 1으로 바꾸었을 때, 바꾸는 횟수의 최솟값
		for (int i = 0; i < m; i++) {
			if (simulatedArr[0][i] == 0) {
				result++;
				toggleCol(simulatedArr, i);
			}
		}

		for (int i = 0; i < n; i++) {
			if (simulatedArr[i][0] == 1) {
				result++;
				toggleRow(simulatedArr, i);
			}
		}

		return isPossible(simulatedArr) ? Math.min(answer, result) : -1;
	}

	private void toggleRow(int[][] simulatedArr, int rowNum) {
		for (int i = 0; i < m; i++) {
			simulatedArr[rowNum][i] = (simulatedArr[rowNum][i] + 1) % 2;
		}
	}

	private void toggleCol(int[][] simulatedArr, int colNum) {
		for (int i = 0; i < n; i++) {
			simulatedArr[i][colNum] = (simulatedArr[i][colNum] + 1) % 2;
		}
	}

	private boolean isPossible(int[][] simulatedArr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (simulatedArr[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Solution131703 solution131703 = new Solution131703();
		System.out.println(
				solution131703.solution(
						new int[][] {
								{0, 1, 0, 0, 0},
								{1, 0, 1, 0, 1},
								{0, 1, 1, 1, 0},
								{1, 0, 1, 1, 0},
								{0, 1, 0, 1, 0}
						},
						new int[][] {
								{0, 0, 0, 1, 1},
								{0, 0, 0, 0, 1},
								{0, 0, 1, 0, 1},
								{0, 0, 0, 1, 0},
								{0, 0, 0, 0, 1}
						})
						== 5);

		System.out.println(
				solution131703.solution(
						new int[][] {
								{0, 0, 0},
								{0, 0, 0},
								{0, 0, 0},
						},
						new int[][] {
								{1, 0, 1},
								{0, 0, 0},
								{0, 0, 0},
						})
						== -1);

		System.out.println(
				solution131703.solution(
						new int[][] {
								{0, 0, 1, 0, 0},
								{1, 0, 0, 0, 0},
								{0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0},
								{0, 0, 0, 0, 0}
						},
						new int[][] {
								{0, 1, 0, 1, 1},
								{0, 0, 0, 0, 0},
								{1, 0, 0, 0, 0},
								{1, 0, 0, 0, 0},
								{1, 0, 0, 0, 0}
						})
						== 2);
	}
}