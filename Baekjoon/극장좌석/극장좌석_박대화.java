import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] vip = new int[m + 2];

        for(int i = 1; i <= m; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }
        vip[0] = 0;
        vip[m + 1] = n + 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (n >= 2) {
            dp[2] = 2;
        }

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 1;

        for(int i = 1; i < m + 2; i++) {
            int len = vip[i] - vip[i - 1] - 1;

            ans *= dp[len];
        }

        System.out.println(ans);
    }
}
