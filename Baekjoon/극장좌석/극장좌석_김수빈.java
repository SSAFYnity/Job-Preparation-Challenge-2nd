import java.io.*;
import java.util.*;


public class 극장좌석_김수빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 좌석 개수
        int M = Integer.parseInt(br.readLine());  // 고정석 개수

        int[] seat = new int[N + 1];

        // 고정석
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            seat[num] = 1;
        }

        // 각 바꿀 수 있는 경우의 수 다 구하기
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (N > 1) {
            dp[2] = 2;
        }
        for (int i = 3; i < N + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }


        // 연속된 자리 수 모두 곱하기
        int ans = 1;
        int lastVIP = 0;

        for (int i = 1; i < N + 1; i++) {
            if (seat[i] == 1) {
                int len = i - lastVIP - 1;
                ans *= dp[len];
                lastVIP = i;
            }
        }

        // 잔여좌석 처리
        int len = N - lastVIP;
        ans *= dp[len];

        System.out.println(ans);

    }
}
