package level3;

import java.util.HashSet;
import java.util.Set;

// (lv3) 불량 사용자 - 성공
public class Solution64064 {
	int USER_LENGTH, BANNED_LENGTH;
	String[] userArr, bannedArr;
	Set<Integer> answer = new HashSet<>();

	boolean isMatched(String w1, String w2) {
		int l1 = w1.length(), l2 = w2.length();
		if (l1 != l2) return false;

		for (int i = 0; i < l1; i++) {
			if (w2.charAt(i) == '*') continue;
			if (w1.charAt(i) != w2.charAt(i))
				return false;
		}
		return true;
	}

	void dfs(int cnt, Integer result) {
		if (cnt == BANNED_LENGTH) {
			answer.add(result);
			return;
		}

		for (int i = 0; i < USER_LENGTH; i++) {
			if ((result & 1 << i) != 0) continue;
			if (isMatched(userArr[i], bannedArr[cnt])) {
				dfs(cnt + 1, result | 1 << i);
			}
		}
	}

	public int solution(String[] user_id, String[] banned_id) {
		answer.clear();
		int result = 0;

		USER_LENGTH = user_id.length;
		BANNED_LENGTH = banned_id.length;
		userArr = user_id;
		bannedArr = banned_id;

		for (int i = 0; i < USER_LENGTH; i++) {
			if (isMatched(user_id[i], banned_id[0])) {
				dfs(1, result | 1 << i);
			}
		}

		return answer.size();
	}

	public static void main(String[] args) {
		Solution64064 solution64064 = new Solution64064();

		System.out.println(solution64064.solution(
				new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"fr*d*", "abc1**"}
		) == 2);
		System.out.println(solution64064.solution(
				new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"*rodo", "*rodo", "******"}
		) == 2);
		System.out.println(solution64064.solution(
				new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"},
				new String[] {"fr*d*", "*rodo", "******", "******"}
		) == 3);
	}
}