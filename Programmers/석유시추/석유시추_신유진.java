import java.util.*;

class Solution {
    static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[][] landCopy;
    static int[][] map;
    static int N, M;
    static int cnt;

    public int solution(int[][] land) {
        int answer = 0;
        N = land.length; // 세로
        M = land[0].length; // 가로

        landCopy = new int[N][M];
        for (int i = 0; i < N; i++) {
            landCopy[i] = land[i].clone();
        }

        int value = 1;
        int[] result = new int[N * M + 1];
        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0 && landCopy[y][x] == 1) {
                    cnt = 0;
                    map[y][x] = value;
                    dfs(x, y);
                    result[value] = cnt;
                    value++;
                }
            }
        }

        for (int col = 0; col < M; col++) {
            int sum = 0;
            boolean[] visit = new boolean[value + 1];
            for (int row = 0; row < N; row++) {
                int idx = map[row][col];
                if (idx > 0 && !visit[idx]) {
                    sum += result[idx];
                    visit[clusterId] = true;
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    public void dfs(int x, int y) {
        cnt++;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;

            if (map[ny][nx] == 0 && landCopy[ny][nx] == 1) {
                map[ny][nx] = map[y][x];
                dfs(nx, ny);
            }
        }
    }
}
