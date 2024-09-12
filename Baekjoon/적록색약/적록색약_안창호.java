import java.util.*;
import java.io.*;

public class 적록색약_안창호 {
    static int N;
    static char[][] picture;
    static int[][] dir = {{0,-1},{0, 1},{1,0},{-1,0}};
    static boolean[][] isVisited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                picture[i][j] = str.charAt(j);
            }
        }

        System.out.print(find() + " ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (picture[i][j] == 'G') {
                    picture[i][j] = 'R';
                }
            }
        }

        isVisited = new boolean[N][N];
        System.out.println(find());

    }

    static int find() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!isVisited[i][j]) {
                    dfs(i, j, picture[i][j]);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void dfs(int x, int y, char color) {
        isVisited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

            if (!isVisited[nx][ny] && picture[nx][ny] == color) {
                dfs(nx, ny, color);
            }
        }
    }

}
