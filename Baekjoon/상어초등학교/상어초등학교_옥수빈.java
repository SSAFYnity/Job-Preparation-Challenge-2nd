import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] map;
    static Map<Integer, List<Integer>> likeMap;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        likeMap = new HashMap<>();
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int like = Integer.parseInt(st.nextToken());
                likeMap.computeIfAbsent(student, k -> new ArrayList<>()).add(like);
            }
            putMap(student);
        }
        System.out.println(countSatisfy());
    }

    public static void putMap(int n) {
        int maxLike = -1;
        int maxEmpty = -1;
        int x = -1;
        int y = -1;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != 0) continue;
                int like = 0;
                int empty = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length) continue;
                    if (likeMap.get(n).contains(map[nx][ny])) like++;
                    if (map[nx][ny] == 0) empty++;
                }

                if (like > maxLike || (like == maxLike && empty > maxEmpty) ||
                        (like == maxLike && empty == maxEmpty && (i < x || (i == x && j < y)))) {
                    maxLike = like;
                    maxEmpty = empty;
                    x = i;
                    y = j;
                }
            }
        }
        map[x][y] = n;
    }

    public static int countSatisfy() {
        int satisfy = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                int like = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length) continue;
                    if (likeMap.get(map[i][j]).contains(map[nx][ny])) like++;
                }
                if (like == 1) satisfy += 1;
                else if (like == 2) satisfy += 10;
                else if (like == 3) satisfy += 100;
                else if (like == 4) satisfy += 1000;
            }
        }
        return satisfy;
    }
}