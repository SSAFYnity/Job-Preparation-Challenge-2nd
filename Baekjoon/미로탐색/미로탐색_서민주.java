import java.util.*;
import java.io.*;

public class Main {
    static int[][] arr;
    static int n, m;
    static class Pair{
        int y, x, count;

        public Pair(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    static boolean[][] visited;
    static LinkedList<Pair> q = new LinkedList<>();



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i<n; i++) {
            String line = br.readLine();
            for (int j = 0; j<m; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        q.add(new Pair(0, 0, 1));
        visited[0][0] = true;

        int[] dy = {0, -1, 0, 1};
        int[] dx = {-1, 0, 1, 0};

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            int y = curr.y, x =curr.x, count = curr.count;
            if (y == n - 1 && x == m - 1) {
                System.out.println(count);
                return;
            }

            for (int i = 0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<m && arr[ny][nx]==1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new Pair(ny, nx, count+1));
                }
            }
        }
    }
}
