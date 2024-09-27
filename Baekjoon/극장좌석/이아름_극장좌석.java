import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int n, m;
    static long ans;
    static long[] dp;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dp = new long[41];
        arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        ans = 1;
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int before = 0;
        for (int i : arr) {
            ans *= dp[i - before - 1];
            before = i;
        }
        ans *= dp[n - before];
        System.out.println(ans);
    }
}