import java.io.*;
import java.util.*;

public class 적록색약_김수빈 {

    public static void main(String[] args) throws IOException {
        // Scanner보다 빠르다고 함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 행렬 길이
        // 적록색약 보드판
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        // 방문 표시
        int[][] normal = new int[N][N];
        int[][] redGreen = new int[N][N];
        // 범위 개수
        int normalArea = 0;
        int redGreenArea = 0;
        // 전체 검사
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (normal[i][j] == 0 || redGreen[i][j] == 0) {
                    int[] output = bfs(N, i, j, normal, redGreen, board);
                    normalArea += output[0];
                    redGreenArea += output[1];
                }
            }
        }
        System.out.printf("%d %d", normalArea, redGreenArea);
    }

    public static int[] bfs(int N, int sr, int sc, int[][] normal, int[][] redGreen, char[][] board) {
        // 방향
        int[] dr = {0, 0, -1, 1};
        int[] dc = {1, -1, 0, 0};
        // 큐
        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        char normalColor = 'W';
        char redGreenColor = 'W';
        // 비적록색약이 방문하지 않은 경우
        if (normal[sr][sc] == 0) {
            normalColor = board[sr][sc];
            queue1.add(new int[]{sr, sc});
            normal[sr][sc] = 1;
        }
        // 적록색약인이 방문하지 않은 경우
        if (redGreen[sr][sc] == 0) {
            redGreenColor = board[sr][sc];
            queue2.add(new int[]{sr, sc});
            redGreen[sr][sc] = 1;
        }
        // 일반 방문
        while (!queue1.isEmpty()) {
            int[] point = queue1.poll();
            for (int i = 0; i < 4; i++) {
                int nr = point[0] + dr[i];
                int nc = point[1] + dc[i];
                if (nr >= 0 && nc >= 0 && nr < N && nc < N && normal[nr][nc] == 0 && board[nr][nc] == normalColor) {
                    queue1.add(new int[]{nr, nc});
                    normal[nr][nc] = 1;
                }
            }
        }
        // 적록색약 방문
        while (!queue2.isEmpty()) {
            int[] point = queue2.poll();
            boolean blue = false;
            if (redGreenColor == 'B') {
                blue = true;
            }
            for (int j = 0; j < 4; j++) {
                int nr = point[0] + dr[j];
                int nc = point[1] + dc[j];
                if (nr >= 0 && nc >= 0 && nr < N && nc < N && redGreen[nr][nc] == 0) {
                    if (blue) {
                        if (board[nr][nc] == 'B') {
                            queue2.add(new int[]{nr, nc});
                            redGreen[nr][nc] = 1;
                        }
                    } else {
                        if (board[nr][nc] == 'R' || board[nr][nc] == 'G') {
                            queue2.add(new int[]{nr, nc});
                            redGreen[nr][nc] = 1;
                        }
                    }
                }
            }
        }
        // 결과
        int[] result = {0, 0};
        if (normalColor != 'W' && redGreenColor != 'W') {
            result[0] = 1;
            result[1] = 1;
        } else if (normalColor != 'W' && redGreenColor == 'W') {
            result[0] = 1;
        } else if (normalColor == 'W' && redGreenColor != 'W') {
            result[1] = 1;
        }
        return result;
    }
}
