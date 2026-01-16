import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Point{
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, cnt;
	static int[][] map, dis;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		dis = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		dis[0][0] = 1;
		BFS(0,0);
		System.out.println(dis[N-1][M-1]);

	}
	static void BFS(int r, int c) {
		Queue<Point> Q = new LinkedList<>();
		Q.offer(new Point(r, c));
		map[r][c] = 0;
		while(!Q.isEmpty()){
			Point tmp = Q.poll();
			if(tmp.r == N-1 && tmp.c == M-1) break;
			for (int i = 0; i < 4; i++) {
				int nr = tmp.r + dr[i];
				int nc = tmp.c + dc[i];
				if(nr >=0 && nr <N && nc >=0 && nc <M && map[nr][nc] == 1) {
					map[nr][nc] = 0;
					Q.offer(new Point(nr, nc));
					dis[nr][nc] = dis[tmp.r][tmp.c]+1;
				}
			}

		}

	}
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};

}
