import java.io.*;
import java.util.*;

class Solution {

    static int[][] map;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows][columns];
        for (int i = 0, index = 1; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = index++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = rotate(query);
        }

        return answer;
    }

    static int rotate(int[] query) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;
        int value = map[x1 + 1][y1];
        // top: x1
        for (int i = y1; i <= y2; i++) {
            int temp = map[x1][i];
            pq.offer(temp);
            map[x1][i] = value;
            value = temp;
        }
        // right: y2
        for (int i = x1 + 1; i <= x2; i++) {
            int temp = map[i][y2];
            pq.offer(temp);
            map[i][y2] = value;
            value = temp;
        }
        // bottom: x2
        for (int i = y2 - 1; i >= y1; i--) {
            int temp = map[x2][i];
            pq.offer(temp);
            map[x2][i] = value;
            value = temp;
        }
        // left: y1
        for (int i = x2 - 1; i >= x1; i--) {
            int temp = map[i][y1];
            pq.offer(temp);
            map[i][y1] = value;
            value = temp;
        }

        return pq.poll();
    }
}