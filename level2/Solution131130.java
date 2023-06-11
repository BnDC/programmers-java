package level2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution131130 {
	public int solution(int[] cards) {
		int answer = 0;
		int length = cards.length;

		boolean[] isOpened = new boolean[length];
		List<List<Integer>> groups = new ArrayList<>();

		for (int i = 0; i < cards.length; i++) {
			List<Integer> group = new ArrayList<>();
			int now = i;
			while (!isOpened[now]) {
				isOpened[now] = true;
				group.add(cards[now]);
				now = cards[now] - 1;
			}
			if (group.size() <= 0)
				continue;
			groups.add(group);
		}

		List<Integer> lengths = groups.stream()
				.map(List::size)
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

		if (lengths.size() >= 2) {
			answer = lengths.get(0) * lengths.get(1);
		}

		return answer;
	}

	public static void main(String[] args) {
		System.out.println(new Solution131130().solution(new int[]{8, 6, 3, 7, 2, 5, 1, 4}) == 12);
	}
}