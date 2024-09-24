import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2302 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];
		for (int i = 0; i < M; i++) {
			visited[Integer.parseInt(br.readLine())] = true;
		}
		
		int[] dp = new int[N + 1];
		dp[1] = 1;
		int combo = (visited[1]) ? 0 : 1;
		for (int i = 2; i <= N; i++) {
			combo++;
			if (visited[i]) {
				dp[i] = dp[i - 1];
				combo = 0;
				continue;
			}
			if (combo == 1) {
				dp[i] = dp[i - 1];
			} else if (combo == 2) {
				dp[i] = dp[i - 1] * 2;
			} else {
				dp[i] = dp[i - 2] + dp[i - 1];
			}
		}
		
		System.out.println(dp[N]);
	}
}
