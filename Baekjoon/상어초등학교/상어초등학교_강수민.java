import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] love = new int[n * n + 1][4];  // 학생들이 좋아하는 학생들 정보
        int[][] result = new int[n][n];        // 교실 자리 배치
        int[] order = new int[n * n];          // 자리 배정 순서

        // 학생들 순서 및 좋아하는 학생 정보 입력받기
        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order[i] = student;
            for (int j = 0; j < 4; j++) {
                love[student][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 학생의 자리를 배정
        for (int i = 0; i < n * n; i++) {
            int student = order[i];
            placeStudent(n, result, love, student);
        }

        // 만족도 계산
        int satisfaction = calculateSatisfaction(n, result, love);
        bw.write(satisfaction + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    // 학생의 자리를 배정하는 함수
    private static void placeStudent(int n, int[][] result, int[][] love, int student) {
        int bestR = -1, bestC = -1;
        int maxLike = -1, maxEmpty = -1;

        // 모든 칸을 순회하며 규칙에 맞는 자리를 찾음
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (result[r][c] != 0) {
                    continue; // 이미 자리가 차있으면 패스
                }

                int likeCount = 0, emptyCount = 0;

                // 인접한 칸을 확인
                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                        continue; // 범위를 벗어나는 경우
                    }

                    if (result[nr][nc] == 0) {
                        emptyCount++; // 빈 칸 카운트
                    } else {
                        for (int j = 0; j < 4; j++) {
                            if (result[nr][nc] == love[student][j]) {
                                likeCount++; // 좋아하는 학생이 인접해있는 경우
                            }
                        }
                    }
                }

                // 좋아하는 학생이 인접한 칸이 더 많거나, 같을 경우 빈칸이 더 많은 경우 선택
                if (likeCount > maxLike || (likeCount == maxLike && emptyCount > maxEmpty)) {
                    bestR = r;
                    bestC = c;
                    maxLike = likeCount;
                    maxEmpty = emptyCount;
                }
            }
        }

        // 찾은 자리에 학생 배치
        result[bestR][bestC] = student;
    }

    // 만족도를 계산하는 함수
    private static int calculateSatisfaction(int n, int[][] result, int[][] love) {
        int totalSatisfaction = 0;

        // 교실을 순회하며 각 학생의 만족도를 계산
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int student = result[r][c];
                int likeCount = 0;

                // 인접한 칸 확인
                for (int[] d : dir) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                        continue;
                    }

                    for (int j = 0; j < 4; j++) {
                        if (result[nr][nc] == love[student][j]) {
                            likeCount++;
                        }
                    }
                }

                // 만족도 계산
                if (likeCount == 1) {
                    totalSatisfaction += 1;
                } else if (likeCount == 2) {
                    totalSatisfaction += 10;
                } else if (likeCount == 3) {
                    totalSatisfaction += 100;
                } else if (likeCount == 4) {
                    totalSatisfaction += 1000;
                }
            }
        }

        return totalSatisfaction;
    }
}