import java.io.*;

public class 오르막수_안창호 {
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][10]; // N+1은 자리수 10은 마지막 자리에 오는 숫자를 의미

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;

            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i -1][j]) % 10007;
            }
        }

        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += dp[N][i];
        }

        System.out.println(answer % 10007);
    }
}
