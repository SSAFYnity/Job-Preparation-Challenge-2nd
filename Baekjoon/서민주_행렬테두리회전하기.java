import java.util.*;

class Solution {

    static int[][] array;

    public int[] solution(int rows, int columns, int[][] queries) {

        array = new int[rows][columns];
        // 행렬 채우기
        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                array[i][j] = count;
                count++;
            }
        }

        int[] answer = new int[queries.length];
        // 회전하기
        for (int k = 0; k < queries.length; k++) {
            int[] query = queries[k];
            int sx = query[0] - 1; // 시작 x
            int sy = query[1] - 1; // 시작 y
            int ex = query[2] - 1; // 끝 x
            int ey = query[3] - 1; // 끝 y

            // 회전 전 첫번째 값을 기억 (우측 하단 값이 이동하게 되므로 미리 저장)
            int prevValue = array[sx][sy];
            int minValue = prevValue;

            // 윗줄 좌->우
            for (int i = sy; i < ey; i++) {
                int temp = array[sx][i + 1];
                array[sx][i + 1] = prevValue;
                prevValue = temp;
                minValue = Math.min(minValue, prevValue);
            }

            // 오른쪽 위->아래
            for (int i = sx; i < ex; i++) {
                int temp = array[i + 1][ey];
                array[i + 1][ey] = prevValue;
                prevValue = temp;
                minValue = Math.min(minValue, prevValue);
            }

            // 아랫줄 우->좌
            for (int i = ey; i > sy; i--) {
                int temp = array[ex][i - 1];
                array[ex][i - 1] = prevValue;
                prevValue = temp;
                minValue = Math.min(minValue, prevValue);
            }

            // 왼쪽 아래->위
            for (int i = ex; i > sx; i--) {
                int temp = array[i - 1][sy];
                array[i - 1][sy] = prevValue;
                prevValue = temp;
                minValue = Math.min(minValue, prevValue);
            }

            answer[k] = minValue;
        }

        return answer;
    }

}