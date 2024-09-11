import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] arr = new char[n][n];
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Pair> que = null;

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    cnt++;
                    que = new ArrayDeque<>();
                    que.add(new Pair(i, j));
                    visit[i][j] = true;

                    while (!que.isEmpty()) {
                        Pair pair = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = pair.r + dx[k];
                            int c = pair.c + dy[k];
                            if (0 <= r && r < n && 0 <= c && c < n && !visit[r][c] && arr[i][j] == arr[r][c]) {
                                visit[r][c] = true;
                                que.add(new Pair(r, c));
                            }
                        }
                    }
                }
            }
        }
        System.out.print(cnt);
        cnt = 0;
        visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    cnt++;
                    que = new ArrayDeque<>();
                    que.add(new Pair(i, j));
                    visit[i][j] = true;

                    while (!que.isEmpty()) {
                        Pair pair = que.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = pair.r + dx[k];
                            int c = pair.c + dy[k];
                            if (0 <= r && r < n && 0 <= c && c < n && !visit[r][c] &&
                                    ((arr[i][j] == 'B' && arr[r][c] == 'B') || (arr[i][j] != 'B' && arr[r][c] != 'B'))) {
                                visit[r][c] = true;
                                que.add(new Pair(r, c));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(" " + cnt);
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}