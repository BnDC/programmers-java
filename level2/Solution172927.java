package level2;

// lv2 광물 캐기 - 성공
class Solution172927 {
	int answer = Integer.MAX_VALUE;
	int length;

	int[] pickax;
	String[] mineralsArr;

	public int solution(int[] picks, String[] minerals) {
		pickax = picks;
		mineralsArr = minerals;
		length = minerals.length;

		dfs(0, 0);
		return answer;
	}

	public void dfs(int idx, int fatigue) {
		if (idx >= length) {
			answer = Math.min(answer, fatigue);
			return;
		}

		if (pickax[0] != 0) {
			pickax[0]--;
			dfs(idx + 5, fatigue + calculateFatigue(0, idx));
			pickax[0]++;
		}
		if (pickax[1] != 0) {
			pickax[1]--;
			dfs(idx + 5, fatigue + calculateFatigue(1, idx));
			pickax[1]++;
		}
		if (pickax[2] != 0) {
			pickax[2]--;
			dfs(idx + 5, fatigue + calculateFatigue(2, idx));
			pickax[2]++;
		}

		if (pickax[0] + pickax[1] + pickax[2] == 0) {
			answer = Math.min(answer, fatigue);
		}
	}

	public int calculateFatigue(int pickax, int start) {
		int result = 0;
		for (int i = start; i < start + 5; i++) {
			if (i >= length)
				break;
			if (pickax == 2 && "diamond".equals(mineralsArr[i]))
				result += 25;
			else if (pickax == 2 && "iron".equals(mineralsArr[i]))
				result += 5;
			else if (pickax == 1 && "diamond".equals(mineralsArr[i]))
				result += 5;
			else
				result++;
		}
		return result;
	}
}
