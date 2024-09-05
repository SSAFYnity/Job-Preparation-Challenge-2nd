import java.util.*;
class Solution {
    public long[] solution(int n, long left, long right) {
        long[] answer = {};
        int size = (int)(right - left + 1);
        answer = new long[size];

        int idx = 0;
        long ln = (long) n;
        long start = (left / ln);
        long leftIdx = left;
        while(true) {
            if (leftIdx == right + 1) break;

            if (leftIdx % ln < start) {
                answer[idx] = start + 1;
            } else {
                answer[idx] = leftIdx % ln + 1;
            }
            idx++;
            leftIdx++;
            if (leftIdx % ln == 0) start++;
        }
        return answer;
    }
}