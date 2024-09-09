import java.io.*;

public class RGB거리_안창호 {

    static int N;
    static int[][] home, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        home = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                home[i][j] = Integer.parseInt(str[j]);
            }
        }
        // 입력 끝

        dp[0][0] = home[0][0]; dp[0][1] = home[0][1]; dp[0][2] = home[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + home[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + home[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + home[i][2];
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[N - 1][i]);
        }

        System.out.println(answer);
    }
}
