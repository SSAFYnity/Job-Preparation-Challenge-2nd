import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = { 0, -1, 1, 0 }; // 상 좌 우 하
    static int[] dy = { -1, 0, 0, 1 }; // 상 좌 우 하
    static int answer = 0;
    static int N;
    static ArrayList<int[]> data;
    static int[][] dataArr;
    static int[][] seats;

    static class Info implements Comparable<Info> {
        int x;
        int y;
        int friendCnt;
        int emptyCnt;

        public Info(int x, int y, int friendCnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.friendCnt = friendCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Info i) {
            if (this.friendCnt == i.friendCnt) {
                if (this.emptyCnt == i.emptyCnt) {
                    if (this.y == i.y) {
                        return this.x - i.x; // 열은 작은순
                    }
                    return this.y - i.y;// 행도 작은순
                }
                return i.emptyCnt - this.emptyCnt; // 빈 칸 많은 순
            }
            return i.friendCnt - this.friendCnt; // 친구 많은 순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = N * N;
        data = new ArrayList<>();
        dataArr = new int[M + 1][5];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] tmp = new int[5];
            tmp[0] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < 5; j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
                dataArr[tmp[0]][j] = tmp[j];
            }
            data.add(tmp);
        }

        int[][] map = new int[N + 1][N + 1];
        seats = new int[M + 1][2];

        map[2][2] = data.get(0)[0];
        seats[data.get(0)[0]] = new int[] { 2, 2 };

        for (int i = 1; i < M; i++) {
            int targetNum = data.get(i)[0]; // 학생 번호
            ArrayList<int[]> locations = new ArrayList<>(); // 해당 학생 앉기 가능한 위치들 저장
            // 좋아하는 학생들 탐색
            for (int j = 1; j < 5; j++) {
                int idx = data.get(i)[j];
                if (seats[idx][0] == 0 && seats[idx][1] == 0) { // 좋아하는 학생이지만 자리 배정 안함
                    continue;
                }

                // 좋아하는 학생의 위치
                int x = seats[idx][0];
                int y = seats[idx][1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx <= 0 || nx > N || ny <= 0 || ny > N || map[ny][nx] != 0)
                        continue;

                    locations.add(new int[] { nx, ny });
                }
            }

            if (locations.isEmpty()) {
                for (int y = 1; y <= N; y++) {
                    for (int x = 1; x <= N; x++) {
                        if (map[y][x] == 0) {
                            locations.add(new int[] { x, y });
                        }
                    }
                }
            }

            // 가능한 위치들을 하나씩 꺼내서 검사 -> 인접 위치에 좋아하는 학생과 빈 칸이 몇 개 있는 지
            PriorityQueue<Info> pq = new PriorityQueue<>();
            for (int[] locate : locations) {
                int emptyCnt = 0;
                int friendCnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = locate[0] + dx[d];
                    int ny = locate[1] + dy[d];
                    if (nx <= 0 || nx > N || ny <= 0 || ny > N)
                        continue;

                    if (map[ny][nx] == 0)
                        emptyCnt++;

                    for (int j = 1; j < 5; j++) {
                        if (data.get(i)[j] == map[ny][nx])
                            friendCnt++;
                    }
                }
                pq.add(new Info(locate[0], locate[1], friendCnt, emptyCnt));
            }

            Info result = pq.poll();
            map[result.y][result.x] = targetNum;
            seats[targetNum] = new int[] { result.x, result.y };
        }

        calculate(map);
        System.out.println(answer);
    }

    static void calculate(int[][] map) {
        for (int i = 1; i <= N * N; i++) { // 학생 번호
            int x = seats[i][0];
            int y = seats[i][1];
            int friendCnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx <= 0 || nx > N || ny <= 0 || ny > N)
                    continue;

                for (int j = 1; j < 5; j++) {
                    if (dataArr[i][j] == map[ny][nx])
                        friendCnt++;
                }
            }

            answer += friendCnt == 4 ? 1000
                    : friendCnt == 3 ? 100 : friendCnt == 2 ? 10 : friendCnt == 1 ? 1 : 0;
        }
    }

    static void print(int[][] map) {
        for (int i = 0; i <= 3; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
