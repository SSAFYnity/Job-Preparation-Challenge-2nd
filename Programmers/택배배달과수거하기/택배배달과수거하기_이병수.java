class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delSum = 0;
        int pickSum = 0;
        for (int i = n - 1; i >= 0; i--){
            delSum += deliveries[i];
            pickSum += pickups[i];
            int cnt = 0;
            while (delSum > 0 || pickSum > 0){
                delSum -= cap;
                pickSum -= cap;
                cnt++;
            }
            answer += (i + 1) * 2 * cnt;
        }
        return answer;
    }
}
