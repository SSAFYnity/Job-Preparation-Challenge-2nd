import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static class house {
		int red, green, blue;

		public house(int red, int green, int blue) {
			this.red = red;
			this.green = green;
			this.blue = blue;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		house[] houses = new house[N];
		// 비용, 마지막 색 저장 배열
		int[][] memo = new int[N][3];

		// 집 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			houses[i] = new house(r, g, b);
		}
		memo[0][0] = houses[0].red;
		memo[0][1] = houses[0].green;
		memo[0][2] = houses[0].blue;

		for (int i = 1; i < N; i++) {
			memo[i][0] = Math.min(memo[i - 1][1], memo[i - 1][2]) + houses[i].red;
			memo[i][1] = Math.min(memo[i - 1][0], memo[i - 1][2]) + houses[i].green;
			memo[i][2] = Math.min(memo[i - 1][0], memo[i - 1][1]) + houses[i].blue;
		}

		int answer = Math.min(memo[N - 1][0], memo[N - 1][1]);
		answer = Math.min(answer, memo[N - 1][2]);

		System.out.println(answer);

	}

}