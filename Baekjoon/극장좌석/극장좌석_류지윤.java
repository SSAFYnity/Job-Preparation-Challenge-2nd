import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

// [BOJ] 극장 좌석
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] fixed = new int[M];
        int[] dp = new int[N+1];
        for (int i = 0; i < M; i++) {
            fixed[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=N; i++){
            dp[i] = dp[i-2] + dp[i-1];
        }

        int ans = 1;
        int idx = 0;
        for (int vip : fixed){
            ans *= dp[vip-idx-1];
            idx = vip;
        }

        ans *= dp[N - idx];
        System.out.println(ans);
    }

}