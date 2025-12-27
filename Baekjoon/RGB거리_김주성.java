import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] A, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][3]; // dp[i][j] := A[i][j] 를 칠했을 때의 최소 비용
        dp[0][0] = A[0][0];
        dp[0][1] = A[0][1];
        dp[0][2] = A[0][2];
        for (int i = 1; i < n; i++) {
            // A[i][j] 와 A[i-1][j]를 매칭해서 최소 비용을 dp[i][j]에 적는다
            // #1 0번 색을 칠했을 때의 최소 비용
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + A[i][0];

            // #2 1번 색을 칠했을 때의 최소 비용
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + A[i][1];

            // #3 2번 색을 칠했을 때의 최소 비용
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + A[i][2];
        }
        System.out.println(Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
    }
}
