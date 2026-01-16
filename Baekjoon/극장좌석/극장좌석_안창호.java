import java.io.*;

public class 극장좌석_안창호 {
    static int N, M;
    static int[] dp;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[41];
        dp[0] = 1; dp[1]= 1; dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        ans = 1;
        int prev = 0;
        for (int i = 0; i < M; i++) {
            int tmp = Integer.parseInt(br.readLine());
            ans *= dp[tmp - prev - 1];
            prev = tmp;
        }

        ans *= dp[N - prev];

        System.out.println(ans);
    }
}
