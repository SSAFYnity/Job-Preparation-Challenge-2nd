import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String map[][] = new String[N + 1][3];
        for (int i = 1; i <= N; i++) {
            map[i] = br.readLine().split(" ");
        }

        int dp[][] = new int[N + 1][3];
        dp[1][0] = Integer.parseInt(map[1][0]);
        dp[1][1] = Integer.parseInt(map[1][1]);
        dp[1][2] = Integer.parseInt(map[1][2]);
        for (int i = 2; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + Integer.parseInt(map[i][0]);
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + Integer.parseInt(map[i][1]);
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + Integer.parseInt(map[i][2]);
        }

        int answer = dp[N][0];
        answer = Math.min(answer, dp[N][1]);
        answer = Math.min(answer, dp[N][2]);
        System.out.println(answer);
    }
}
