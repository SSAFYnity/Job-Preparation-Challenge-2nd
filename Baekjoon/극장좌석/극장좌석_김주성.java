import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 극장좌석_김주성 {

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        vips = new int[M];
        for (int i = 0; i < M; i++) vips[i] = Integer.parseInt(br.readLine());
    }

    static void pro() {
        ans = 1;

        dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int start = 0;
        for (int i = 0; i < M; i++) {
            int end = vips[i];
            ans *= dp[end - start - 1];
            start = end;
        }
        ans *= dp[N - start];
    }

    static int[] dp, vips;
    static int ans, N, M;

    public static void main(String[] args) throws IOException {
        input();
        pro();
        System.out.println(ans);
    }
}
