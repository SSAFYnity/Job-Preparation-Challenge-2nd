import java.io.*;
import java.util.*;

public class 적록색약_여창영 {
    static int N;
    static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    static char place[][];
    static Deque<Pos> que;
    static StringBuilder sb;

    public static void bfs(int c) {
        // c : 0 => 일반 c : 1 => 적록색맹

        int cnt = 0;
        boolean isVisited[][] = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j]) {
                    continue;
                }

                cnt++;
                que.clear();
                que.add(new Pos(i, j));
                isVisited[i][j] = true;

                while (!que.isEmpty()) {
                    Pos cur = que.poll();

                    for (int idx = 0; idx < 4; idx++) {
                        int di = cur.i + dir[idx][0];
                        int dj = cur.j + dir[idx][1];

                        if (isValid(di, dj) && !isVisited[di][dj]) {
                            if (place[cur.i][cur.j] == place[di][dj]) {
                                que.add(new Pos(di, dj));
                                isVisited[di][dj] = true;
                            } else if (c == 1 && isColorDisorder(cur.i, cur.j, di, dj)) {
                                que.add(new Pos(di, dj));
                                isVisited[di][dj] = true;
                            }
                        }
                    }
                }
            }
        }

        sb.append(cnt).append(" ");
    }

    public static boolean isValid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public static boolean isColorDisorder(int i, int j, int a, int b) {
        if ((place[i][j] == 'R' && place[a][b] == 'G') || (place[i][j] == 'G' && place[a][b] == 'R')) {
            return true;
        }
        return false;
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        place = new char[N][N];
        que = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                place[i][j] = input.charAt(j);
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        bfs(0);
        bfs(1);
        System.out.println(sb);
    }

    static class Pos {
        int i, j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}