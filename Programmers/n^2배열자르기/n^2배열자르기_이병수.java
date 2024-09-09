import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        
        for (long i = left; i <= right; i++){
            int row = (int) (i / n);
            int col = (int) (i % n);
            answer[(int) (i - left)] = Math.max(row + 1, col + 1);
        }
        
        return answer;
    }
}
