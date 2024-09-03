import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        for(long i=left; i<=right; i++) {
            answer[(int)(i - left)] = f(i, n);
        }
        return answer;
    }
    private static int f(long idx, int n) {
        int i = (int)(idx / n);
        int j = (int)(idx % n);
        return Math.max(i, j) + 1;
    }
}
