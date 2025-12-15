import java.util.Scanner;

public class Main {
	static int n, cnt;
	static char[][] arr;
	static boolean[][] visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new char[n][n];
		visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String line = sc.next();
			for (int j = 0; j < n; j++) {
				arr[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {
					DFS(i,j);
					cnt++;
				}
			}
		}

		int normal_cnt = cnt;
		cnt = 0;
		visit = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(arr[i][j]=='G') arr[i][j] = 'R';
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(!visit[i][j]) {
					DFS(i,j);
					cnt++;
				}
			}
		}

		int abnormal_cnt = cnt;

		System.out.println(normal_cnt+" "+abnormal_cnt);
	}
	static void DFS(int r, int c){
		visit[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >=0 && nr<n && nc >=0 && nc<n && !visit[nr][nc] && arr[nr][nc]==arr[r][c]){
				DFS(nr,nc);
			}
		}
	}
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
}
