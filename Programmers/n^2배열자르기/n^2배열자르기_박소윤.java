import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left) + 1];
        for (int i = 0; i < answer.length; i++) {
            int r = (int)((left + i) / n);
            int c = (int)((left + i) % n);
            answer[i] = Math.max(r + 1, c + 1);
        }
        return answer;
    }
}