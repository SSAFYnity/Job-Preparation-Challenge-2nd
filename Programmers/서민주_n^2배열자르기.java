import java.util.*;

class Solution {
    public LinkedList<Integer> solution(long n, long left, long right) {
        LinkedList<Integer> answer = new LinkedList<>();

        for (long i = left; i<right+1; i++) {
            answer.add(Math.toIntExact(Math.max(i / n, i % n) + 1));
        }
        return answer;
    }
}