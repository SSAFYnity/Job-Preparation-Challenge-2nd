import java.util.*;

class Solution {
    class House {
        int len, d, p;
        public House(int len, int d, int p) {
            this.len = len;
            this.d = d;
            this.p = p;
        }
    }
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        ArrayDeque<House> que = new ArrayDeque<>();
        for (int i = n-1; i >= 0; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0) {continue;}
            que.add(new House(i+1, deliveries[i], pickups[i]));
        }
        
        int dcnt = 0;
        int pcnt = 0;
        
        while (!que.isEmpty()) {
            House now = que.poll();

            int tempD = Math.min(now.d, dcnt);
            int tempP = Math.min(now.p, pcnt);
            now.d -= tempD;
            now.p -= tempP;
            dcnt -= tempD;
            pcnt -= tempP;
            
            if (now.d <= 0 && now.p <= 0) {continue;}
            
            int cnt = (int) Math.ceil((double) Math.max(now.d, now.p) / cap);
            
            dcnt += cap * cnt - now.d;
            pcnt += cap * cnt - now.p;
            
            answer += now.len * 2 * cnt;
        }
        
        return answer;
    }
}