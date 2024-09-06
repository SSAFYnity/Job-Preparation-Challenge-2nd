import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] dp = new int [N+1][3];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int red     = Integer.parseInt(st.nextToken());
            int green   = Integer.parseInt(st.nextToken());
            int blue    = Integer.parseInt(st.nextToken());

            dp[i][0] = red + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = green + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = blue + Math.min(dp[i-1][0],dp[i-1][1]);
        }

        Arrays.sort(dp[N]);
        System.out.println(dp[N][0]);
    }
}