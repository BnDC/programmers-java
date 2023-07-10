package level2;

public class Solution148652 {
	public int solution(int n, long l, long r) {
		int answer = 0;

		for (long i = l - 1; i < r; i++) {
			if (isOne(i))
				answer++;
		}
		return answer;
	}

	boolean isOne(long num) {
		if (num % 5 == 2)
			return false;
		if (num < 5)
			return true;
		return isOne(num / 5);
	}

	public static void main(String[] args) {
		System.out.println(new Solution148652().solution(2, 4, 17) == 8);
	}
}
