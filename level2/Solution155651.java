package level2;

public class Solution155651 {
	public int solution(String[][] book_time) {
		int answer = 0;
		int MAX_TIME = 24 * 60;
		int[] time = new int[MAX_TIME];

		for(int i = 0; i < book_time.length; i++) {
			int start = parseTime(book_time[i][0]);
			int end = parseTime(book_time[i][1]);
			end += 10;

			time[start] += 1;
			if (end < MAX_TIME) time[end]--;
		}

		for (int i = 1; i < MAX_TIME; i++) {
			time[i] += time[i - 1];
			answer = Math.max(answer, time[i]);
		}

		return answer;
	}

	private int parseTime(String t) {
		String[] time = t.split(":");
		return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
	}

	public static void main(String[] args) {
		Solution155651 Solution = new Solution155651();
		System.out.println(Solution.solution(new String[][] {
				{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}
		}) == 3);

		System.out.println(Solution.solution(new String[][] {
				{"09:10", "10:10"}, {"10:20", "12:20"}
		}) == 1);

		System.out.println(Solution.solution(new String[][] {
				{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}
		}) == 3);
	}

}