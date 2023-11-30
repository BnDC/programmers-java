package level2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// lv2 광물 캐기 - 실패
class Solution172927 {
	int answer = Integer.MAX_VALUE;

	int[] permutation = new int[3];
	String[] mineralsArr;
	int[] pickax;

	Map<Fatigue, Integer> map = new HashMap<>();

	public int solution(int[] picks, String[] minerals) {
		init();

		mineralsArr = minerals;
		pickax = picks;

		dfs(0, 0);

		return answer;
	}

	public void init() {
		map.put(new Fatigue(0, "diamond"), 1);
		map.put(new Fatigue(0, "iron"), 1);
		map.put(new Fatigue(0, "stone"), 1);

		map.put(new Fatigue(1, "diamond"), 5);
		map.put(new Fatigue(1, "iron"), 1);
		map.put(new Fatigue(1, "stone"), 1);

		map.put(new Fatigue(2, "diamond"), 25);
		map.put(new Fatigue(2, "iron"), 5);
		map.put(new Fatigue(2, "stone"), 1);
	}

	public void dfs(int cnt, int flag) {
		if (cnt >= 3) {
			answer = Math.min(answer, mine());
			return;
		}

		for (int i = 0; i < 3; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			permutation[cnt] = i;
			dfs(cnt + 1, flag | (1 << i));
		}
	}

	public int mine() {
		int[] copyOfPickax = new int[3];
		System.arraycopy(pickax, 0, copyOfPickax, 0, 3);

		int result = 0;
		int mineralIdx = 0;
		int mineralLength = mineralsArr.length;

		loop:
		for (int i = 0; i < 3; i++) {
			int now = permutation[i];
			while (copyOfPickax[now] > 0) {
				for (int j = 0; j < 5; j++) {
					if (mineralIdx >= mineralLength)
						break loop;
					result += map.get(new Fatigue(now, mineralsArr[mineralIdx]));
					mineralIdx++;
				}
				--copyOfPickax[now];
			}
		}
		return result;
	}

	static class Fatigue {
		int pickax;
		String mineral;

		public Fatigue(int pickax, String mineral) {
			this.pickax = pickax;
			this.mineral = mineral;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || this.getClass() != obj.getClass())
				return false;
			Fatigue fatigue = (Fatigue)obj;
			return fatigue.mineral.equals(this.mineral) && fatigue.pickax == this.pickax;
		}

		@Override
		public int hashCode() {
			return Objects.hash(pickax, mineral);
		}
	}
}
