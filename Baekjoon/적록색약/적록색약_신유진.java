import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int N;
    static boolean[][] v1;
    static String[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        data = new String[N];
        for (int i = 0; i < N; i++) {
            data[i] = br.readLine();
        }

        v1 = new boolean[N][N];
        int result1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v1[i][j]) {
                    result1 += bfs(j, i);
                }
            }
        }

        v1 = new boolean[N][N];
        int result2 = 0;
        for (int i = 0; i < N; i++) {
            data[i] = data[i].replaceAll("G", "R");
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!v1[i][j]) {
                    result2 += bfs(j, i);
                }
            }
        }

        System.out.println(result1 + " " + result2);
    }

    public static int bfs(int x, int y) {
        Queue<int[]> q1 = new LinkedList<>();
        q1.add(new int[] { x, y });
        Character value = data[y].charAt(x);
        v1[y][x] = true;

        while (!q1.isEmpty()) {
            int[] tmp = q1.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N || v1[ny][nx])
                    continue;

                if (value == data[ny].charAt(nx)) {
                    v1[ny][nx] = true;
                    q1.add(new int[] { nx, ny });
                }
            }
        }
        return 1;
    }
}
