import java.io.*;
import java.util.*;

class Solution {

    public int[] solution(int n, long left, long right) {
        int N = (int) (right - left + 1);
        int[] answer = new int[N];
        int index = 0;
        for(long i = left; i <= right; i++){
            long x = i / n;
            long y = i % n;
            answer[index++] = (int)(Math.max(x, y) + 1);
        }
        return answer;
    }
}