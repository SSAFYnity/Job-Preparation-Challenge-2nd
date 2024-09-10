package 적록색약;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};
    static char[][] map;
    static int n;

    public static void bfs(int i, int j, char color, boolean[][] visited, boolean is_rg) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{i, j});
        visited[i][j] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];

                if(ni >= 0 && ni < n && nj >= 0 && nj < n && !visited[ni][nj]) {
                    if((is_rg && color != 'B' && map[ni][nj] != 'B') || map[ni][nj] == color) {
                        visited[ni][nj] = true;
                        q.offer(new int[]{ni, nj});
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][];

        for(int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
        // end input

        boolean[][] visited = new boolean[n][n];
        boolean[][] rg_visited = new boolean[n][n];

        int cnt = 0;
        int rg_cnt = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    bfs(i, j, map[i][j], visited, false);
                    cnt++;
                }
                if(!rg_visited[i][j]) {
                    bfs(i, j, map[i][j], rg_visited, true);
                    rg_cnt++;
                }
            }
        }

        System.out.println(cnt + " " + rg_cnt);

    }
}
