import java.util.*;

class Solution {
    public List<Long> solution(int n, long left, long right) {
        List<Long> answer = new ArrayList<>();
        for(long i=left;i<=right;i++){
            long r=i/n; long c=i%n;
            answer.add(Math.max(r,c)+1);
        }
        return answer;
    }
}