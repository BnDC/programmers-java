package level2;

// lv2 광물 캐기 - 성공
public class Solution160585 {
	public int solution(String[] board) {
		int o = 0, x = 0;
		int oWins = 0, xWins = 0;

		// o, x 객수 세기
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i].charAt(j) == 'O')
					o++;
				else if (board[i].charAt(j) == 'X')
					x++;
			}
		}

		// row 빙고 확인
		for (int i = 0; i < 3; i++) {
			if (board[i].charAt(1) == board[i].charAt(0)
					&& board[i].charAt(1) == board[i].charAt(2)
			) {
				if (board[i].charAt(1) == 'O')
					oWins++;
				else if (board[i].charAt(1) == 'X')
					xWins++;
			}
		}
		// col 빙고 확인
		for (int i = 0; i < 3; i++) {
			if (board[1].charAt(i) == board[0].charAt(i)
					&& board[1].charAt(i) == board[2].charAt(i)
			) {
				if (board[1].charAt(i) == 'O')
					oWins++;
				else if (board[1].charAt(i) == 'X')
					xWins++;
			}
		}

		// 대각선 빙고 확인
		if (board[1].charAt(1) == board[0].charAt(0)
				&& board[1].charAt(1) == board[2].charAt(2)
		) {
			if (board[1].charAt(1) == 'O')
				oWins++;
			else if (board[1].charAt(1) == 'X')
				xWins++;
		}

		if (board[1].charAt(1) == board[0].charAt(2)
				&& board[1].charAt(1) == board[2].charAt(0)
		) {
			if (board[1].charAt(1) == 'O')
				oWins++;
			else if (board[1].charAt(1) == 'X')
				xWins++;
		}

		// 틱택토 판정
		if (o - x > 1 || x > o) {
			return 0;
		}

		if (oWins + xWins > 1) {
			if (oWins == 2 && xWins == 0) {
				return x == 4 ? 1 : 0;
			}
			return 0;
		}

		if (oWins == 1 && o - x != 1) {
			return 0;
		}
		if (xWins == 1 && o - x != 0) {
			return 0;
		}
		return 1;
	}

	public static void main(String[] args) {
		Solution160585 solution = new Solution160585();
		System.out.println(solution.solution(
				new String[]{
						"O.X",
						".O.",
						"..X"
				}
		) == 1);
		System.out.println(solution.solution(
				new String[]{
						"OOO",
						"...",
						"XXX"
				}
		) == 0);
		System.out.println(solution.solution(
				new String[]{
						"...",
						".X.",
						"..."
				}
		) == 0);
		System.out.println(solution.solution(
				new String[]{
						"...",
						"...",
						"..."
				}
		) == 1);
	}
}
