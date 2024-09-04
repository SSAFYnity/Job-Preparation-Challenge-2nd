import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] dp = new int[N][3];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < 3; i++) {
            dp[0][i] = map[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k == j) continue;
                    dp[i][j] = Math.min(map[i][j] + dp[i - 1][k], dp[i][j]);
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            ans = Math.min(ans, dp[N - 1][i]);
        }
        System.out.println(ans);
    }
}