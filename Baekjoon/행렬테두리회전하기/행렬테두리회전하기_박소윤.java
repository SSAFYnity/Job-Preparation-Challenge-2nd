import java.util.*;

class Solution {
    public List<Integer> solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        int[][] matrix = new int[rows + 1][columns + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = columns * (i - 1) + j;
            }
        }
        for (int[] query : queries) {
            answer.add(rotateAndFindMinChanged(
                matrix, query[0], query[1], query[2], query[3]));
        }
        return answer;
    }
    
    private int rotateAndFindMinChanged(
        int[][] matrix, int x1, int y1, int x2, int y2) {
        
        int start = matrix[x1][y1];
        int min = matrix[x1][y1];
        for (int i = y1 + 1; i <= y2; i++) {
            start = swap(matrix, x1, i, start);
            min = Math.min(min, matrix[x1][i]);
        }
        for (int i = x1 + 1; i <= x2; i++) {
            start = swap(matrix, i, y2, start);
            min = Math.min(min, matrix[i][y2]);
        }
        for (int i = y2 - 1; i >= y1; i--) {
            start = swap(matrix, x2, i, start);
            min = Math.min(min, matrix[x2][i]);
        }
        for (int i = x2 - 1; i >= x1; i--) {
            start = swap(matrix, i, y1, start);
            min = Math.min(min, matrix[i][y1]);
        }
        return min;
    }
    
    private int swap(int[][] matrix, int x, int y, int value) {
        int temp = matrix[x][y];
        matrix[x][y] = value;
        return temp;
    }
}