import java.util.*;
import java.io.*;

class Solution {
    // 나누기를 통해 1을 만들 수 있기에, number가 1~ 32000 중 뭐든 간에 결국엔 만들 수 있다.
    // 하지만 문제에서는 N 등장횟수가 8보다 크면 그냥 -1로 출력하라고 했다. 이 시점에서 N 등장횟수가 8번 이하인 녀석들만 계산하면 된다는 것이다.

    /*
        dp[k] 는 N을 K번 사용한 값들의 모임 (dp 원소마다 set으로 값을 모아놓을 것임.)
        dp[k] 는 dp[l] 와 dp[k-l] 의 조합으로 나오는 모든 수 (1 <= l < k)
    */
    public int solution(int N, int number) {
        if(N == number) return 1;
        // 0. 값 초기화
        Set<Integer> [] dp = new Set [9];
        for(int i = 1; i < 9; i++){
            dp[i] = new HashSet<>();
        }
        // 1. dp[1]부터 dp[8]까지 차근차근 계산
        dp[1].add(N);
        int accN = N;
        for(int i = 2; i< 9; i++){
            accN = accN*10+N;
            if(accN == number) return i;
            dp[i].add(accN);

            for(int j = 1; j < i; j++){
                for(int k : dp[j]){
                    for(int l : dp[i-j]){
                        int p = k+l; int s = k-l; int m = k*l; int d = l >0? k/l : 0;
                        if( p == number || s == number || m == number || d == number) return i;
                        dp[i].add(p);
                        dp[i].add(s);
                        dp[i].add(m);
                        if(d != 0) dp[i].add(d);
                    }
                }
            }
        }
        return -1;
    }
}