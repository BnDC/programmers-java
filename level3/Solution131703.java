package level3;

// (lv3) 2차원 동전 뒤집기 - 실패
public class Solution131703 {
	int n, m;

	public int solution(int[][] beginning, int[][] target) {
		int answer;
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

		System.out.println("### printDiff ###");
		printArr(diffArr);

		answer = getMinRowFirst(simulatedArr);
		for (int i = 0; i < n; i++) {
			System.arraycopy(diffArr[i], 0, simulatedArr[i], 0, m);
		}

		answer = Math.min(answer, getMinColFirst(simulatedArr));
		return answer != Integer.MAX_VALUE ? answer : -1;
	}

	private int getMinRowFirst(int[][] simulatedArr) {
		int result = toggleRow(simulatedArr) + toggleCol(simulatedArr);
		if (!isPossible(simulatedArr))
			return Integer.MAX_VALUE;
		return result;
	}

	private int getMinColFirst(int[][] simulatedArr) {
		int result = toggleCol(simulatedArr) + toggleRow(simulatedArr);
		if (!isPossible(simulatedArr))
			return Integer.MAX_VALUE;
		return result;
	}

	private int toggleRow(int[][] simulatedArr) {
		int result = 0;
		boolean flag;
		for (int i = 0; i < n; i++) {
			flag = false;
			for (int j = 0; j < m; j++) {
				if (simulatedArr[i][j] == 1) {
					flag = true;
					result++;
					break;
				}
			}
			if (flag) {
				for (int j = 0; j < m; j++) {
					simulatedArr[i][j] = (simulatedArr[i][j] + 1) % 2;
				}
			}
		}

		System.out.println("### toggleRow ###");
		printArr(simulatedArr);

		return result;
	}

	private int toggleCol(int[][] simulatedArr) {
		int result = 0;
		boolean flag;
		for (int i = 0; i < m; i++) {
			flag = false;
			for (int j = 0; j < n; j++) {
				if (simulatedArr[j][i] == 1) {
					flag = true;
					result++;
					break;
				}
			}
			if (flag) {
				for (int j = 0; j < m; j++) {
					simulatedArr[i][j] = (simulatedArr[i][j] + 1) % 2;
				}
			}
		}

		System.out.println("### toggleCol ###");
		printArr(simulatedArr);

		return result;
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

	void printArr(int[][] arr) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
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
						}));
	}
}