import java.util.*;
import java.io.*;

public class Main {

    public static boolean isOut(int y, int x, int n) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 좋아하는친구많은순 > 빈칸많은순 > 행작은 > 열작은
        // 만족도 0이 아니라면 Math.pow(10, 좋친-1)
        int n = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][] room = new int[n][n];
        int[] student = new int[n*n]; // 자리 찾는 순서에 학생 번호가 값
        Set<Integer>[] que = new HashSet[n*n];
        Map<Integer, Integer> map = new HashMap<>(); // 학생이 몇번째로 자리 찾는지

        for (int i = 0; i < n*n; i++) {
            st = new StringTokenizer(br.readLine());
            student[i] = Integer.parseInt(st.nextToken());
            que[i] = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                que[i].add(Integer.parseInt(st.nextToken()));
            }
            map.put(student[i], i);
        }
        room[1][1] = student[0];

        int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};

        for (int k = 1; k < n * n; k++) {
            int maxlike = 0;
            int maxblank = 0;
            int y = -1;
            int x = -1;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (room[i][j] != 0) {
                        continue;
                    }

                    int like = 0;
                    int blank = 0;
                    for (int d = 0; d < 4; d++) {
                        int dy = i + dr[0][d];
                        int dx = j + dr[1][d];
                        if (isOut(dy, dx, n)) {
                            continue;
                        }
                        if (room[dy][dx] == 0) blank++;
                        else if (que[k].contains(room[dy][dx])) like++;
                    }

                    if (maxlike < like) {
                        y = i;
                        x = j;
                        maxlike = like;
                        maxblank = blank;
                    } else if (maxlike == like && maxblank < blank) {
                        y = i;
                        x = j;
                        maxblank = blank;
                    } else if (y == -1) {
                        y = i;
                        x = j;
                    }
                }
            }
            room[y][x] = student[k];
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(room[i]));
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cnt = 0;
                int s = room[i][j];
                for (int d = 0; d < 4; d++) {
                    int dy = i + dr[0][d];
                    int dx = j + dr[1][d];
                    if (isOut(dy, dx, n)) continue;
                    if (que[map.get(s)].contains(room[dy][dx])) cnt++;
                }
                if (cnt == 0) {continue;}
                else answer += Math.pow(10, cnt-1);
            }
        }

        System.out.println(answer);
    }
}

