import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 기본판 만들기
        int[][] square = new int[rows][columns];
        int n = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                square[i][j] = n;
                n++;
            }
        }
        // 정답 배열
        int[] answer = new int[queries.length];
        // 행 하나씩 돌리기
        for (int i = 0; i < queries.length; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            // 시작 위치
            int cr = x1;
            int cc = y1;
            // 이동 경로
            int[] dr = {0, 1, 0, -1};
            int[] dc = {1, 0, -1, 0};
            int d = 0;
            // 이동해야하는 숫자와 최소값 확인
            int moveNum = square[x1][y1];
            int minNum = moveNum;

            while (true) {
                // 다음 방향
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                // 끝지점 가면 방향 틀기
                if (cr > x2 || cr < x1 || cc > y2 || cc < y1) {
                    d++;
                    continue;
                }
                // 옮기기
                int temp = square[nr][nc];
                square[nr][nc] = moveNum;
                moveNum = temp;
                // 제일 작은 수면 바꿔주기
                if (moveNum < minNum) {
                    minNum = moveNum;
                }
                // 이동
                cr = nr;
                cc = nc;
                // 도착지점으로 돌아오면 중단
                if (cr == x1 && cc == y1) {
                    break
                }
            }
            // 작은수 넣기
            answer[i] = minNum;
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // 예시 입력
        int[][] queries = {
                {2, 2, 5, 4},
                {3, 3, 6, 6},
                {5, 1, 6, 3}
        };
        int[] result = sol.solution(6, 6, queries);

        // 결과 출력
        System.out.println(Arrays.toString(result));
    }
}