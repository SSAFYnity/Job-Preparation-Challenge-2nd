class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int q_len = queries.length;
        int[] answer = new int[q_len];
        int[][] arr = new int[rows][columns];

        // 배열 초기화
        for (int i = 0, cnt = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = cnt++;
            }
        }

        // 쿼리 처리
        for (int i = 0; i < q_len; i++) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;

            // 회전 전 첫 번째 값을 저장
            int temp = arr[x1][y1];
            int minVal = temp;

            // 왼쪽 열 회전
            for (int j = x1; j < x2; j++) {
                arr[j][y1] = arr[j + 1][y1];
                minVal = Math.min(minVal, arr[j][y1]);
            }

            // 아래 행 회전
            for (int j = y1; j < y2; j++) {
                arr[x2][j] = arr[x2][j + 1];
                minVal = Math.min(minVal, arr[x2][j]);
            }

            // 오른쪽 열 회전
            for (int j = x2; j > x1; j--) {
                arr[j][y2] = arr[j - 1][y2];
                minVal = Math.min(minVal, arr[j][y2]);
            }

            // 위쪽 행 회전
            for (int j = y2; j > y1; j--) {
                arr[x1][j] = arr[x1][j - 1];
                minVal = Math.min(minVal, arr[x1][j]);
            }

            // 첫 번째 값을 마지막으로 이동
            arr[x1][y1 + 1] = temp;

            // 최소값을 답에 저장
            answer[i] = minVal;
        }

        return answer;
    }
}
