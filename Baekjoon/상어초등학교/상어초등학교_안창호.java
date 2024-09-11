import java.io.*;
import java.util.*;

public class 상어초등학교_안창호 {
    static int N;
    static int[][] dir = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    static int[][] classroom;
    static List<Integer> students;
    static Map<Integer, int[]> likeMap;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N + 1][N + 1];
        students = new ArrayList<>();
        likeMap = new HashMap<>();

        for (int i = 1; i <= N*N; i++) {
            String[] str = br.readLine().split(" ");

            students.add(Integer.parseInt(str[0]));

            int[] tmp = new int[4];

            for (int j = 1; j < 5; j++) {
                tmp[j - 1] = Integer.parseInt(str[j]);
            }

            likeMap.put(Integer.parseInt(str[0]), tmp);
        }
        // 입력 끝

        for (int student : students) {
            List<int[]> likeList = new ArrayList<>();
            // 조건 1.비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
            // 비어있는 칸 찾기
            int max = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (classroom[i][j] == 0) {
                        int like = findLikeClose(i, j, student);
                        // 좋아하는 학생이 더 많은 칸을 발견
                        if (like > max) {
                            likeList.clear();
                            max = like;
                            likeList.add(new int[] {i, j});
                        } else if (like == max) {
                            likeList.add(new int[] {i, j});
                        }
                    }
                }
            }
            // 조건 1을 만족하는 칸이 하나라면
            if (likeList.size() == 1) {
                int[] tmp = likeList.get(0);
                classroom[tmp[0]][tmp[1]] = student;
            } else if (likeList.size() > 1) { // 조건 1을 만족하는 칸이 여러 개라면
                // 조건 2.인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다
                List<int[]> blankList = findBlank(likeList);

                // 조건 2를 만족하는 칸이 하나라면
                if (blankList.size() == 1) {
                    int[] tmp = blankList.get(0);
                    classroom[tmp[0]][tmp[1]] = student;
                } else if (blankList.size() > 1) { // 조건 2를 만족하는 칸이 여러 개라면
                    // 행을 오름차순으로, 행이 같다면 열을 오름차순으로 정렬
                    Collections.sort(blankList, (o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);
                    int[] tmp = blankList.get(0);
                    classroom[tmp[0]][tmp[1]] = student;
                }
            }
        }

        // 만족도 구하기
        getSatisfaction();
        System.out.println(answer);
        br.close();
    }
    static void getSatisfaction() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int student = classroom[i][j];
                int like = findLikeClose(i, j, student);

                if (like == 0) {
                    answer += 0;
                } else if (like == 1) {
                    answer += 1;
                } else if (like == 2) {
                    answer += 10;
                } else if (like == 3) {
                    answer += 100;
                } else if (like == 4) {
                    answer += 1000;
                }
            }
        }
    }
    static List<int[]> findBlank(List<int[]> list) {
        List<int[]> ret = new ArrayList<>();

        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int[] tmp = list.get(i);
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nx = tmp[0] + dir[d][0];
                int ny = tmp[1] + dir[d][1];

                if (nx <= 0 || ny <= 0 || nx > N || ny > N || classroom[nx][ny] != 0) continue;

                cnt++;
            }
            if (cnt > max) {
                ret.clear();
                max = cnt;
                ret.add(new int[] {tmp[0], tmp[1]});
            } else if (cnt == max) {
                ret.add(new int[] {tmp[0], tmp[1]});
            }
        }
        return ret;
    }

    static int findLikeClose(int x, int y, int student) {
        int[] likeMember = likeMap.get(student);
        int cnt = 0;

        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];

            if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;

            for (int i = 0; i < 4; i++) {
                if (classroom[nx][ny] == likeMember[i]) {
                    cnt++;
                    break;
                }
            }
        }
        return cnt;
    }
}
