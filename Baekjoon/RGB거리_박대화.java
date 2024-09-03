import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    int[][] map = new int[n][3];
    int[][] dp = new int[n][3]; // i번까지 칠한 비용의 최솟값

    for(int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0; j < 3; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        dp[i][j] = Integer.MAX_VALUE;
      }
    }

    dp[0][0] = map[0][0];
    dp[0][1] = map[0][1];
    dp[0][2] = map[0][2];
    for(int i = 1; i < n; i++) {
      for(int j = 0; j < 3; j++) {
        if(j != 0) {
          dp[i][j] = Math.min(dp[i - 1][0] + map[i][j], dp[i][j]);
        }
        if(j != 1) {
          dp[i][j] = Math.min(dp[i - 1][1] + map[i][j], dp[i][j]);
        }
        if(j != 2) {
          dp[i][j] = Math.min(dp[i - 1][2] + map[i][j], dp[i][j]);
        }

      }
    }

    System.out.println(Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2])));
  }
}