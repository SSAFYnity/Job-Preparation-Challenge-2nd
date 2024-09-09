package G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G10026 {
	static int N;
	static char[][] map;
	static boolean[][] visited;
	static int cnt;
	static int weakCnt;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		visited = new boolean[N][N];
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, false);
					cnt++;
				}
			}
		}
		visited = new boolean[N][N];
		weakCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j, true);
					weakCnt++;
				}
			}
		}
		System.out.println(cnt + " " + weakCnt);
	}
	
	static void bfs(int r, int c, boolean forWeak) { // forWeak : 적록색약인지 아닌지
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r, c, map[r][c]));
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int nr, nc;
			for (int i = 0; i < 4; i++) {
				nr = curNode.r + dr[i];
				nc = curNode.c + dc[i];
				if (forWeak && (curNode.color == 'R' || curNode.color == 'G')) {
					if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] 
							&& (map[nr][nc] == 'R' || map[nr][nc] == 'G')) {
						queue.add(new Node(nr, nc, curNode.color));
						visited[nr][nc] = true;
					}
				} else {
					if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc] 
							&& map[nr][nc] == curNode.color) {
						queue.add(new Node(nr, nc, curNode.color));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static class Node{
		int r;
		int c;
		char color;
		
		public Node(int r, int c, char color) {
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}
}
