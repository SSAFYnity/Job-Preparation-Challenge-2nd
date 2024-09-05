import java.util.*;
class Solution {
    public ArrayList<Long> solution(int n, long left, long right) {
        ArrayList<Long> answer = new ArrayList<>();
        for (long i = left; i <= right; i++) {
            answer.add(Math.max((i / n), (i % n)) + 1);
        }
        return answer;
    }
}