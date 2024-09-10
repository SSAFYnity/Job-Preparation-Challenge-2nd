public class 택배배달과수거하기_안창호 {
    public static void main(String[] args) {
        class Solution {
            public long solution(int cap, int n, int[] deliveries, int[] pickups) {
                long answer = 0;

                // 음수가 된다는 것은 아직 배달/픽업이 되지 않았음을 의미
                int d = 0; // 배달 해야 할 택배의 총량
                int p = 0; // 픽업 해야 할 택배의 총량

                for (int i = n - 1; i >= 0; i--) { // 먼 곳부터 처리
                    // 각 지점에서 배달과 픽업할 택배 수를 총량에서 차감
                    d -= deliveries[i];
                    p -= pickups[i];

                    while (d < 0 || p < 0) { // 배달 또는 픽업할 물품이 남아 있다면
                        // 최대로 실을 수 있는 만큼 배달/픽업 진행
                        d += cap;
                        p += cap;

                        // 왕복 거리 추가
                        answer += (i + 1) * 2;
                    }
                }

                return answer;
            }
        }
    }
}
