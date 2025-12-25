class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int didx = n-1; // 배달 idx
        int pidx = n-1; // 픽업 idx

        while(true){
            if (didx < 0 && pidx < 0) break;
            int cur = cap; // 트럭에 싣고 갈 짐
            int pick  = 0;
            int d = -1;
            int p = -1;
            while(cur > 0 && didx != -1){
                if(deliveries[didx] > 0){
                    d = d > didx ? d : didx; // 이동 거리 갱신
                    deliveries[didx]--;
                    cur--;
                } else  {
                    didx--;
                }
            }
            while(pick < cap && pidx != -1){
                if (pickups[pidx] > 0){
                    p = p > pidx ? p : pidx; // 이동 거리 갱신
                    pickups[pidx]--;
                    pick++;
                } else {
                    pidx--;
                }
            }
            answer += Math.max(p+1, d+1) * 2;
        }

        return answer;
    }
}