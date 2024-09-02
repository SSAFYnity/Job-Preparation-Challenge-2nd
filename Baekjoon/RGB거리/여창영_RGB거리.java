import java.io.*;
import java.util.*;

public class 여창영_RGB거리 {
    static int N, result;
    static int rgb[][], dp[][];

    public static void dynamic() {
        dp[0][0] = rgb[0][0];
        dp[0][1] = rgb[0][1];
        dp[0][2] = rgb[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(rgb[i][0] + dp[i - 1][1], rgb[i][0] + dp[i - 1][2]);
            dp[i][1] = Math.min(rgb[i][1] + dp[i - 1][0], rgb[i][1] + dp[i - 1][2]);
            dp[i][2] = Math.min(rgb[i][2] + dp[i - 1][0], rgb[i][2] + dp[i - 1][1]);
        }

        result = Math.min((Math.min(dp[N - 1][0], dp[N - 1][1])), dp[N - 1][2]);
    }

    public static void pre() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        rgb = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String args[]) throws IOException {
        pre();
        dynamic();
        System.out.println(result);
    }
}