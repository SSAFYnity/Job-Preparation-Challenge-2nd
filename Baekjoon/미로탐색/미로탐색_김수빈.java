import java.io.*;
import java.util.*;

public class 미로탐색_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] inputArr = input.split(" ");
        int N = Integer.parseInt(inputArr[0]);  // N개 줄
        int M = Integer.parseInt(inputArr[1]);  // M개의 정수

        char[][] maze = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            maze[i] = line.toCharArray();
        }
        // 방문기록
        int[][] visited = new int[N][M];
        // 방향
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        // 너비우선탐색
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = 1;

        int minNum = 10000;


        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            // 도착
            if (x == N - 1 && y == M - 1) {
                minNum = visited[N-1][M-1];
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 안이고 방문하지 않았을 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (maze[nx][ny] == '1' && visited[nx][ny] == 0) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = visited[x][y] + 1;
                    }
                }

            }
        }
        System.out.printf("%d", minNum);
    }
}
