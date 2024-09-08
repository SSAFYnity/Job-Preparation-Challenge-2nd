import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {1,-1,0,0};
    private static boolean[][] visitedA;
    private static boolean[][] visitedB;
    private static char[][] map;
    private static int rgb = 0;
    private static int rb = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visitedA = new boolean[n][n];
        visitedB = new boolean[n][n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(!visitedA[i][j]) BFS_A(i, j);
                if(!visitedB[i][j]) BFS_B(i, j);
            }
        }
        System.out.println(rgb);
        System.out.println(rb);
    }
    private static void BFS_B(int x, int y) {
        rb++;
        char color = map[x][y];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visitedB[x][y] = true;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if (!(nx >= 0 && nx < map.length && ny >= 0 && ny < map.length)) continue;
                if (visitedB[nx][ny]) continue;
                if (map[nx][ny] != color && (map[nx][ny] == 'B' || color == 'B')) continue;
                visitedB[nx][ny] = true;

                q.offer(new int[]{nx, ny});
            }
        }
    }
    private static void BFS_A(int x, int y) {
        rgb++;
        char color = map[x][y];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visitedA[x][y] = true;
        while(!q.isEmpty()) {
            int[] tmp = q.poll();

            for(int i=0; i<4; i++) {
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if(!(nx>=0 && nx<map.length && ny>=0 && ny<map.length)) continue;
                if(visitedA[nx][ny]) continue;
                if(map[nx][ny] != color) continue;
                visitedA[nx][ny] = true;

                q.offer(new int[]{nx, ny});
            }
        }
    }
}

