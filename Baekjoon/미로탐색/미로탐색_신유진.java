import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] maze;
    static Queue<int[]> q;
    static int[] dx = { 0, 0, -1, 1 }; // 우 좌 상 하
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 값 받기
        maze = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = (int) (s.charAt(j) - '0');
            }
        }

        q = new ArrayDeque<>();
        q.add(new int[] { 0, 0 });
        bfs();
        System.out.println(maze[N - 1][M - 1]);
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int x = tmp[0];
            int y = tmp[1];
            int nx, ny;
            for (int d = 0; d < 4; d++) {
                nx = x + dx[d];
                ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || maze[nx][ny] != 1)
                    continue;
                maze[nx][ny] = maze[x][y] + 1;
                q.add(new int[] { nx, ny });
            }
        }
    }

}
