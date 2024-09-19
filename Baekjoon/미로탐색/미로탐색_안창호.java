import java.io.*;
import java.util.*;

public class 미로탐색_안창호 {
    static int N, M;
    static int[][] maze;
    static boolean[][] visited;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(maze[N - 1][M - 1]);
    }
    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dir[d][0];
                int ny = now[1] + dir[d][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || maze[nx][ny] == 0) continue;

                maze[nx][ny] = maze[now[0]][now[1]] + 1;
                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

}
