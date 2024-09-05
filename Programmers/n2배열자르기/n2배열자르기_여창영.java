import java.io.*;
import java.util.*;

public class n2배열자르기_여창영 {
    public int[] solution(int n, long left, long right) {
        long len = right - left;
        int[] answer = new int[(int)len + 1];

        int idx = 0;
        for (long i = left; i <= right; i++) {
            long row = i / n;
            long col = i % n;
            answer[idx++] = Math.max((int)row, (int)col) + 1;
        }
        
        return answer;
    }
}