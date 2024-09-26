import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        // N을 1~8번 사용한 경우를 저장할 DP 테이블
        Set<Integer>[] dp = new Set[9];

        for (int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
        }

        for (int i = 1; i < 9; i++) {
            // N을 i번 연속해서 쓴 값을 미리 넣음
            dp[i].add(Integer.parseInt(String.valueOf(N).repeat(i)));

            // i번 사용해서 만들 수 있는 모든 수들을 구하기
            for (int j = 1; j < i; j++) {
                for (int op1 : dp[j]) {
                    for (int op2 : dp[i - j]) {
                        dp[i].add(op1 + op2);   // 덧셈
                        dp[i].add(op1 - op2);   // 뺄셈
                        dp[i].add(op1 * op2);   // 곱셈
                        if (op2 != 0) {
                            dp[i].add(op1 / op2);  // 나눗셈
                        }
                    }
                }
            }

            // 만약 dp[i]에 number가 있다면, i를 반환
            if (dp[i].contains(number)) {
                return i;
            }
        }

        // 끝까지 찾지 못하면 -1 반환
        return -1;
    }
}
