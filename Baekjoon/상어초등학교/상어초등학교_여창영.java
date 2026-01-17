import java.io.*;
import java.util.*;

public class 상어초등학교_여창영 {
    static int N, score;
    static int map[][], empty[][];
    static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static HashMap<Integer, Student> hstd;
    public static List<Student> std;

    public static void impl() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                empty[i][j] = 4;
                if (i == 0 || i == N - 1) {
                    empty[i][j]--;
                }
                if (j == 0 || j == N - 1) {
                    empty[i][j]--;
                }
            }
        }

        map[1][1] = std.get(0).std_num;
        empty[0][1]--;
        empty[2][1]--;
        empty[1][0]--;
        empty[1][2]--;

        for (int k = 1; k < N * N; k++) {
            Student s = std.get(k);
            int like[][] = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0)
                        continue;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dir[d][0];
                        int ny = j + dir[d][1];
                        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                            for (int s_n : s.like) {
                                if (s_n == map[nx][ny]) {
                                    like[i][j]++;
                                }
                            }
                        }
                    }
                }
            }

            int max_empty = -1;
            int max_like = -1;
            int row = -1;
            int col = -1;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0)
                        continue;
                    if (like[i][j] > max_like) {
                        max_like = like[i][j];
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    }
                    else if (like[i][j] == max_like && empty[i][j] > max_empty) {
                        max_empty = empty[i][j];
                        row = i;
                        col = j;
                    }
                }
            }

            map[row][col] = s.std_num;
            hstd.put(s.std_num, s);
            for (int d = 0; d < 4; d++) {
                int nx = row + dir[d][0];
                int ny = col + dir[d][1];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    empty[nx][ny]--;
                }
            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < std.size(); k++) {
                    if (std.get(k).std_num == map[i][j]) {
                        for (int d = 0; d < 4; d++) {
                            int nx = i + dir[d][0];
                            int ny = j + dir[d][1];
                            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                                for (int s_n : std.get(k).like) {
                                    if (map[nx][ny] == s_n) {
                                        cnt++;
                                    }
                                }
                            }
                        }

                    }
                }

                if (cnt == 1) {
                    score++;
                }
                else if (cnt == 2) {
                    score += 10;
                }
                else if (cnt == 3) {
                    score += 100;
                }
                else if (cnt == 4) {
                    score += 1000;
                }

            }
        }
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        score = 0;

        map = new int[N][N];
        empty = new int[N][N];

        std = new ArrayList<>();
        hstd = new HashMap<>();

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int l[] = new int[4];

            for (int j = 1; j < 5; j++) {
                l[j - 1] = Integer.parseInt(st.nextToken());
            }
            std.add(new Student(num, l));
        }
    }

    public static void main(String[] args) throws IOException {
        pre();
        impl();
        System.out.println(score);
    }

    static class Student {
        int std_num;
        int like[];

        public Student(int std_num, int like[]) {
            this.std_num = std_num;
            this.like = like;
        }
    }
}