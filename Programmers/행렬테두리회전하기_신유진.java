import java.util.*;
class Solution {
    static int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {

        int[] answer = new int[queries.length];

        map = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = (i - 1) * columns + j;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }

        return answer;
    }

    public static int rotate(int[] query) {
        int x1 = query[0], y1 = query[1];
        int x2 = query[2], y2 = query[3];

        int temp = map[x1][y1];
        int minValue = temp;

        for (int i = x1; i < x2; i++) {
            map[i][y1] = map[i + 1][y1];
            minValue = Math.min(minValue, map[i][y1]);
        }

        for (int i = y1; i < y2; i++) {
            map[x2][i] = map[x2][i + 1];
            minValue = Math.min(minValue, map[x2][i]);
        }

        for (int i = x2; i > x1; i--) {
            map[i][y2] = map[i - 1][y2];
            minValue = Math.min(minValue, map[i][y2]);
        }

        for (int i = y2; i > y1; i--) {
            map[x1][i] = map[x1][i - 1];
            minValue = Math.min(minValue, map[x1][i]);
        }

        map[x1][y1 + 1] = temp;

        return minValue;
    }
}