class 택배배달과수거하기_강민정 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivery = 0;       // 배달할 물건 갯수
        int pickup = 0;         // 수거할 물건 갯수

        // 가장 먼곳부터 배달 및 수거를 해야 함
        for(int i=n-1; i>=0; i--) {
            delivery += deliveries[i];      // i번째 집에 배달할 택배 갯수 추가
            pickup += pickups[i];           // i번째 집에서 수거할 택배 갯수 추가

            while(delivery > 0 || pickup > 0) {     // 배달 또는 수거할 물건이 있으면
                delivery -= cap;
                pickup -= cap;
                answer += (i + 1) * 2;      // 왕복으로 이동
            }
        }

        return answer;  // 최단 이동 거리
    }
}