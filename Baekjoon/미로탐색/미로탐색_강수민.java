import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j + 1] = chars[j] == '1';
            }
        }


        boolean[][] visited = new boolean[N + 1][M + 1];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> que = new ArrayDeque<>();
        que.add(new Point(1, 1));
        visited[1][1] = true;
        int cnt = 1;
        boolean flag = false;

        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                Point point = que.poll();
                if (point.row == N && point.col == M) {
                    flag = true;
                    break;
                }
                for (int j = 0; j < 4; j++) {
                    int row = point.row + dx[j];
                    int col = point.col + dy[j];
                    if (0 < row && row <= N && 0 < col && col <= M && map[row][col] && !visited[row][col]) {
                        visited[row][col] = true;
                        que.add(new Point(row, col));
                    }
                }
            }
            if (flag) break;
            cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}