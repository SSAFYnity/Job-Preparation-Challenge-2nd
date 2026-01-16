import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> emptySeat = new ArrayList<>();
        int pre = 0;
        for (int i = 0; i < M; i++) {
            int data = Integer.parseInt(br.readLine());
            int value = data - pre - 1;
            if (value != 0) {
                emptySeat.add(data - pre - 1);
            }
            pre = data;
        }

        if (M == 0) {
            emptySeat.add(N);
        } else if (pre != N) {
            emptySeat.add(N - pre);
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int result = 1;
        // list가 비어있을 시 모두 vip임
        if (!emptySeat.isEmpty()) {
            for (Integer v : emptySeat) {
                result *= dp[v];
            }
        }

        System.out.println(result);
    }

}
