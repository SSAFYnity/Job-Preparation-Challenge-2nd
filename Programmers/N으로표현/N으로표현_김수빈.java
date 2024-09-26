import java.util.*;

public class N으로표현_김수빈 {
    class Solution {
        public int solution(int N, int number) {
            Set<Integer>[] dp = new Set[9];

            for (int i = 1; i < 9; i++) {
                dp[i] = new HashSet<>();
                // String.valueOf(N) 정수를 문자열로 변환하는 메서드
                // repeat(count) 메서드 문자열 count번 반복하여 새로운 문자열 반환
                String repeatedN = String.valueOf(N).repeat(i);
                dp[i].add(Integer.parseInt(repeatedN));
            }

            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < i; j++) {
                    for (int x : dp[j]) {
                        for (int y : dp[i-j]) {
                            dp[i].add(x + y);
                            dp[i].add(x - y);
                            dp[i].add(x * y);
                            if (y != 0) {
                                dp[i].add(x / y);
                            }
                        }
                    }
                }
                if (dp[i].contains(number)) {
                    return i;
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        N으로표현_김수빈 outerClass = new N으로표현_김수빈();
        Solution solutionInstance = outerClass.new Solution();

        int N = 2;
        int number = 11;

        int result = solutionInstance.solution(N, number);
        System.out.println(result);

    }


}
