import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

class N으로표현_강민정 {
    public int solution(int N, int number) {
        int answer = -1;    // N 사용횟수가 8보다 크면 -1
        String strN = String.valueOf(N);
        List<Set<Integer>> dp = new ArrayList();

        if(N == number) {      // 사칙연산없이 만들 수 있다면
            return 1;
        }

        // 초기화
        dp.add(new HashSet());
        dp.get(0).add(Integer.parseInt(strN));
        for(int i=1; i<9; i++) {
            dp.add(new HashSet());
            dp.get(i).add(Integer.parseInt(strN));
            strN += String.valueOf(N);
        }

        for(int i=2; i<9; i++) {        // 숫자 N 사용 횟수
            for(int x=1; x<i; x++) {
                int y = i - x;      // x + y = i인 dp[x]와 dp[y]를 사칙연산한다
                for(int j : dp.get(x)) {
                    for(int k : dp.get(y)) {
                        dp.get(i).add(j + k);
                        if(j - k > 0) {
                            dp.get(i).add(j - k);
                        }
                        dp.get(i).add(j * k);
                        if(k != 0 && j/k > 0) {
                            dp.get(i).add(j / k);
                        }
                        if(j != 0 && k/j > 0) {
                            dp.get(i).add(k / j);
                        }
                    }
                }
                if(dp.get(i).contains(number)) {    // number를 만들었다면
                    return i;     // N 사용횟수의 최솟값
                }
            }
        }
        return -1;          // 불가능
    }
}