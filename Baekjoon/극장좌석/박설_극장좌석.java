import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];//1 1 2 3
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		int answer = 1;
		int start = 0;

		for (int i = 0; i < M; i++) {
			int temp = Integer.parseInt(br.readLine());
			answer *= dp[temp - start - 1];
			start = temp;
		}
		answer *= dp[N - start];
		System.out.println(answer);
	}
}
