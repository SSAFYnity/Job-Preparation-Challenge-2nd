import java.util.*;

class Solution {
    static int[][] square;
    static int rows, columns;
    
    public int rotate(int[] q) {
        int num = Integer.MAX_VALUE;
        int minY = q[0]-1;
        int maxY = q[2]-1;
        int minX = q[1]-1;
        int maxX = q[3]-1;
        
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i = minX; i <= maxX; i++) {
            que.add(square[minY][i]);
            num = Math.min(num, square[minY][i]);
        }
        for (int i = minY+1; i <= maxY; i++) {
            que.add(square[i][maxX]);
            num = Math.min(num, square[i][maxX]);
        }
        for (int i = maxX-1; i >= minX; i--) {
            que.add(square[maxY][i]);
            num = Math.min(num, square[maxY][i]);
        }
        for (int i = maxY-1; i > minY; i--) {
            que.add(square[i][minX]);
            num = Math.min(num, square[i][minX]);
        }
        
        square[minY][minX] = que.pollLast();
        for (int i = minX+1; i <= maxX; i++) {
            square[minY][i] = que.poll();
        }
        for (int i = minY+1; i <= maxY; i++) {
            square[i][maxX] = que.poll();
        }
        for (int i = maxX-1; i >= minX; i--) {
            square[maxY][i] = que.poll();
        }
        for (int i = maxY-1; i > minY; i--) {
            square[i][minX] = que.poll();
        }
        
        return num;
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        square = new int[rows][columns];
        int s = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                square[i][j] = s++;
            }
        }
        
        int a = 0;
        for (int[] q : queries) {
            answer[a] = rotate(q);
            a++;
        }
        return answer;
    }
}