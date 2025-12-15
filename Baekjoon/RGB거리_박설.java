import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] DP = new int[N+1][3];

		for(int i=1;i<=N;i++) {
			int R = sc.nextInt();
			int G = sc.nextInt();
			int B = sc.nextInt();

			DP[i][0] = Math.min(DP[i-1][1], DP[i-1][2]) + R;
			DP[i][1] = Math.min(DP[i-1][0], DP[i-1][2]) + G;
			DP[i][2] = Math.min(DP[i-1][0], DP[i-1][1]) + B;
		}

		System.out.println(Math.min(DP[N][0], Math.min(DP[N][1], DP[N][2])));

	}

}
