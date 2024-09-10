import java.util.Queue;
import java.util.ArrayDeque;
import java.io.*;

class 적록색약_강민정 {
    static boolean[][] visit;
    static int n;
    static String[] grid;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int normalCnt = 0;      // 적록색약이 아닌 사람이 봤을 때 구역의 개수
        int cnt = 0;            // 적록색약인 사람이 봤을 때 구역의 개수
        n = Integer.parseInt(br.readLine());    // 1 <= 배열 크기 <= 100
        grid = new String[n];
        visit = new boolean[n][n];

        for(int i=0; i<n; i++) {
            grid[i] = br.readLine();
        }

        // 적록색약이 아닌 사람의 구역 갯수 세기
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(visit[i][j]) {
                    continue;
                }
                normalBfs(i, j, grid[i].charAt(j));
                normalCnt++;
            }
        }

        // 적록색약인 사람의 구역 갯수 세기
        visit = new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(visit[i][j]) {
                    continue;
                }
                bfs(i, j, grid[i].charAt(j));
                cnt++;
            }
        }

        System.out.println(normalCnt + " " + cnt);
    }

    /*
        적록색약이 아닌 사람의 BFS 탐색
     */
    public static void normalBfs(int x, int y, char color) {
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[]{x, y});
        visit[x][y] = true;

        while(!que.isEmpty()) {
            int[] cur = que.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(!isValid(nx, ny)) {      // 인덱스 범위를 넘어가면
                    continue;
                }

                if(visit[nx][ny] || grid[nx].charAt(ny) != color) {      // 방문했거나 시작점과 색깔이 다르면
                    continue;
                }

                que.add(new int[]{nx, ny});
                visit[nx][ny] = true;
            }

        }
    }

    /*
        적록색약인 사람의 BFS 탐색
     */
    public static void bfs(int x, int y, char color) {
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[]{x, y});
        visit[x][y] = true;

        while(!que.isEmpty()) {
            int[] cur = que.poll();

            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(!isValid(nx, ny)) {      // 인덱스 범위를 넘어가면
                    continue;
                }

                if(visit[nx][ny] || (grid[nx].charAt(ny) != color && (color == 'B' || grid[nx].charAt(ny) == 'B'))) {      // 방문했거나 시작점과 색깔이 다르면
                    continue;
                }

                que.add(new int[]{nx, ny});
                visit[nx][ny] = true;
            }

        }
    }

    /*
        인덱스 범위가 유효하면 true 리턴
     */
    public static boolean isValid(int nx, int ny) {
        return 0 <= nx && 0 <= ny && nx < n && ny < n;
    }
}