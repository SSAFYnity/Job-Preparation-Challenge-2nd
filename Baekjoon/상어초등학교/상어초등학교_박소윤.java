import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    static int N;
    static int[][] classroom;
    static Map<Integer, Set<Integer>> likeMap = new HashMap<>();
    static Map<Integer, Integer> satisfactionMap = Map.of(
            0, 0,
            1, 1,
            2, 10,
            3, 100,
            4, 1000);
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            String[] input = br.readLine().split(" ");
            int student = Integer.parseInt(input[0]);
            Set<Integer> liked = new HashSet<>();
            for (int j = 1; j < input.length; j++) {
                liked.add(Integer.parseInt(input[j]));
            }
            likeMap.put(student, liked);
            findSeat(student, liked);
        }
        int satisfaction = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Set<Integer> liked = likeMap.get(classroom[i][j]);
                int likeCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (isOutOfBounds(nx, ny)) {
                        continue;
                    }
                    if (liked.contains(classroom[nx][ny])) {
                        likeCount++;
                    }
                }
                satisfaction += satisfactionMap.get(likeCount);
            }
        }
        System.out.println(satisfaction);
    }

    private static void findSeat(int student, Set<Integer> liked) {
        int[] position = new int[2];
        int maxLikeCount = -1;
        int maxEmpty = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] != 0) {
                    continue;
                }
                int likeCount = 0;
                int empty = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    // 범위 초과 처리
                    if (isOutOfBounds(nx, ny)) {
                        continue;
                    }
                    // 인접한 칸에 좋아하는 학생
                    if (liked.contains(classroom[nx][ny])) {
                        likeCount++;
                    }
                    // 빈 자리
                    if (classroom[nx][ny] == 0) {
                        empty++;
                    }
                }
                if (maxLikeCount < likeCount ||
                        (maxLikeCount == likeCount && maxEmpty < empty)) {
                    maxLikeCount = likeCount;
                    maxEmpty = empty;
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        classroom[position[0]][position[1]] = student;
    }

    private static boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
