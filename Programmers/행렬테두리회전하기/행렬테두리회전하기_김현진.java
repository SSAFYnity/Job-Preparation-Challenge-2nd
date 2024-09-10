class 행렬테두리회전하기_김현진 {
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];

		int[][] board = new int[rows + 1][columns + 1];

		int num = 1;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				board[i][j] = num++;
			}
		}

		for (int i = 0; i < queries.length; i++) {
			int x1 = queries[i][0];
			int y1 = queries[i][1];
			int x2 = queries[i][2];
			int y2 = queries[i][3];

			int temp = board[x1][y1];

			int min = temp;

			// 좌측 테두리 이동
			for (int j = x1; j < x2; j++) {
				board[j][y1] = board[j + 1][y1];
				min = Math.min(min, board[j][y1]);
			}

			// 하단 테두리 이동
			for (int j = y1; j < y2; j++) {
				board[x2][j] = board[x2][j + 1];
				min = Math.min(min, board[x2][j]);
			}

			// 우측 테두리 이동
			for (int j = x2; j > x1; j--) {
				board[j][y2] = board[j - 1][y2];
				min = Math.min(min, board[j][y2]);
			}

			// 상단 테두리 이동
			for (int j = y2; j > y1; j--) {
				board[x1][j] = board[x1][j - 1];
				min = Math.min(min, board[x1][j]);
			}

			board[x1][y1 + 1] = temp;

			answer[i] = min;
		}

		return answer;
	}
}