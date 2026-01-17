import java.io.*;
import java.util.*;

public class 구슬탈출2_여창영{
	static int N, M, cnt;
	static int ri, rj, bi, bj, idx, rix, rjy, bix, bjy, cntRedMove, cntBlueMove;
	static char board[][];
	static int depth[][][][];
	static boolean isVisited[][][][];
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static dot red, blue, Hole, input;
	static Deque<dot> redQue;
	static Deque<dot> blueQue;

	public static void countMarbleMove() {
		while (isNotWallOrHole(rix, rjy)) {
			rix += dx[idx];
			rjy += dy[idx];
			cntRedMove++;
		}

		while (isNotWallOrHole(bix, bjy)) {
			bix += dx[idx];
			bjy += dy[idx];
			cntBlueMove++;
		}

		rix -= dx[idx];
		rjy -= dy[idx];
		bix -= dx[idx];
		bjy -= dy[idx];
	}

	public static void bfs() {

		redQue.add(red);
		blueQue.add(blue);

		while (!redQue.isEmpty()) {
			red = redQue.poll();
			blue = blueQue.poll();

			ri = red.i;
			rj = red.j;
			bi = blue.i;
			bj = blue.j;

			if (depth[ri][rj][bi][bj] > 10) {
				System.out.println("-1");
				return;
			}

			for (idx = 0; idx < 4; idx++) {
				rix = ri + dx[idx];
				rjy = rj + dy[idx];
				bix = bi + dx[idx];
				bjy = bj + dy[idx];
				cntRedMove = 0;
				cntBlueMove = 0;

				countMarbleMove();
				if (bix + dx[idx] == Hole.i && bjy + dy[idx] == Hole.j) { // 파란 구슬이 구멍에 빠지는 경우
					continue;
				}
				if (rix + dx[idx] == Hole.i && rjy + dy[idx] == Hole.j) { // 빨간 구슬이 구멍에 빠지는 경우
					if (depth[ri][rj][bi][bj] >= 10) {
						System.out.println("-1");
					}
					else {
						System.out.println(depth[ri][rj][bi][bj] + 1);
					}
					return;
				}

				if (rix == bix && rjy == bjy) { // 빨간 구슬과 파란 구슬이 같은 위치인 경우
					if (cntRedMove > cntBlueMove) {
						rix -= dx[idx];
						rjy -= dy[idx];
					} else {
						bix -= dx[idx];
						bjy -= dy[idx];
					}
				}

				if (ri == rix && rj == rjy && bi == bix && bj == bjy) {
					continue;
				}

				if (depth[rix][rjy][bix][bjy] == 0) {
					depth[rix][rjy][bix][bjy] = depth[ri][rj][bi][bj] + 1;
				} else {

					depth[rix][rjy][bix][bjy] = Math.min(depth[rix][rjy][bix][bjy], depth[ri][rj][bi][bj] + 1);
				}

				if (isVisited[rix][rjy][bix][bjy]) {
					continue;
				}

				input = new dot(rix, rjy);

				redQue.add(input);

				input = new dot(bix, bjy);
				blueQue.add(input);
				isVisited[rix][rjy][bix][bjy] = true;

			}

		}

		System.out.println("-1"); // 큐를 다 탐색해도 결과가 안 나올 경우 = 불가능한 경우
	}

	public static boolean isNotWallOrHole(int i, int j) {
		if (board[i][j] == '#') {
			return false;
		}
		if (i == Hole.i && j == Hole.j) {
			return false;
		}
		return true;
	}

	public static void pre() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = 0;

		board = new char[N][M];
		depth = new int[N][M][N][M];
		isVisited = new boolean[N][M][N][M];
		redQue = new ArrayDeque<>();
		blueQue = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			String inp[] = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = inp[j].charAt(0);
				depth[i][j][i][j] = 10;
				if (board[i][j] == 'R') {
					red = new dot(i, j);
				}
				if (board[i][j] == 'B') {
					blue = new dot(i, j);
				}
				if (board[i][j] == 'O') {
					Hole = new dot(i, j);
				}
			}
		}

		isVisited[red.i][red.j][blue.i][blue.j] = true;
		depth[red.i][red.j][blue.i][blue.j] = 0;
	}

	public static void main(String args[]) throws IOException {
		pre();
		bfs();
	}

	static class dot {
		int i, j;

		public dot() {
		}

		public dot(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}