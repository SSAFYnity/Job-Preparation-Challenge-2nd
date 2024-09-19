import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static int bfs(int n, int m, int[][] map) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new int[]{0, 0, 1});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == n - 1 && cur[1] == m - 1) {
                return cur[2];
            }

            for(int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];

                if(ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj] && map[ni][nj] == 1) {
                    visited[ni][nj] = true;
                    q.offer(new int[]{ni, nj, cur[2] + 1});
                }

            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for(int i = 0; i < n; i++) {
            char[] list = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(list[j]));
            }
        }


        System.out.println(bfs(n, m, map));

    }
}
