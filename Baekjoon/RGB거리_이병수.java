import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] costs = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
		}
		
		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
	}
}
