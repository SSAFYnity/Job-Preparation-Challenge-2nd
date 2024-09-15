class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        long dSum = 0;
        long pSum = 0;

        int count = 0;     // 반복 횟수
        for(int i = n-1; i >= 0; i--){
            if(deliveries[i] == 0 && pickups[i] == 0) continue;

            count = 0;
            while(dSum < deliveries[i] || pSum < pickups[i]){
                count++;
                dSum += cap;      // 최대 횟수 만큼
                pSum += cap;
            }

            dSum -= deliveries[i];
            pSum -= pickups[i];
            answer += (i+1) * count * 2; // (배달-수거)거리
        }

        return answer;
    }
}