import java.io.*;

public class Main {
    static int n;
    static char [][] arr;
    static int count1 = 0; // 일반 사람들이 봤을 때의 구역의 수
    static int count2 = 0; // 적록색약 사람들이 봤을 때의 구역의 수
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visited1;
    static boolean[][] visited2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        visited1 = new boolean[n][n];
        visited2 = new boolean[n][n];
        for (int i = 0; i<n; i++) {
            String line = br.readLine();
            for (int j = 0; j<n; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i<n; i++) {
            for (int j = 0; j<n; j++) {
                if (!visited1[i][j]) {
                    count1 += dfs(i, j);
                }
                if (!visited2[i][j]) {
                    count2 += dfs2(i, j, arr[i][j]);
                }
            }
        }
        System.out.println(count1 + " " + count2);
    }

    // 일반 사람들이 느끼는 것
    public static int dfs(int y, int x) {
        visited1[y][x] = true;
        for (int i = 0; i<4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if (ny>=0 && ny<n && nx>=0 && nx<n && arr[ny][nx]==arr[y][x] && !visited1[ny][nx]) {
                visited1[ny][nx] = true;
                dfs(ny, nx);
            }
        }
        return 1;
    }

    // 적록색맹인 사람들이 느끼는 것
    public static int dfs2(int y, int x, char a) {
        visited2[y][x] = true;
        if (a == 'B') {
            for (int i = 0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<n && arr[ny][nx]=='B' && !visited2[ny][nx]) {
                    visited2[ny][nx] = true;
                    dfs2(ny, nx, arr[ny][nx]);
                }
            }
        } else {
            for (int i = 0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                if (ny>=0 && ny<n && nx>=0 && nx<n && arr[ny][nx]!='B' && !visited2[ny][nx]) {
                    visited2[ny][nx] = true;
                    dfs2(ny, nx, arr[ny][nx]);
                }
            }
        }
        return 1;
    }
}